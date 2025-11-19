package com.etiya.salesservice.service.concretes;

import com.etiya.common.events.order.CreateOrderEvent;
import com.etiya.common.requests.CreateProdCharValueRequest;
import com.etiya.common.requests.CreateProductRequest;
import com.etiya.common.responses.*;
import com.etiya.salesservice.client.CatalogServiceClient;
import com.etiya.salesservice.client.CustomerServiceClient;
import com.etiya.salesservice.domain.*;
import com.etiya.salesservice.repository.OrderRepository;
import com.etiya.salesservice.service.abstracts.OrderService;
import com.etiya.salesservice.service.dtos.requests.CreateOrderRequest;
import com.etiya.salesservice.service.dtos.requests.OrderItemRequest;
import com.etiya.salesservice.service.dtos.requests.ProductCharacteristicRequest;
import com.etiya.salesservice.service.dtos.responses.CreatedOrderResponse;
import com.etiya.salesservice.service.dtos.responses.GetOrderResponse;
import com.etiya.salesservice.service.mappings.OrderAddressMapper;
import com.etiya.salesservice.service.mappings.OrderBillingAccountMapper;
import com.etiya.salesservice.service.mappings.OrderCustomerMapper;
import com.etiya.salesservice.service.mappings.OrderMapper;
import com.etiya.salesservice.transport.kafka.producer.OrderCreatedProducer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerServiceClient customerServiceClient;
    private final CatalogServiceClient catalogServiceClient;
    private final OrderCreatedProducer orderCreatedProducer;

    public OrderServiceImpl(OrderRepository orderRepository, CustomerServiceClient customerServiceClient, CatalogServiceClient catalogServiceClient, OrderCreatedProducer orderCreatedProducer) {
        this.orderRepository = orderRepository;
        this.customerServiceClient = customerServiceClient;
        this.catalogServiceClient = catalogServiceClient;
        this.orderCreatedProducer = orderCreatedProducer;
    }

    @Override
    @Transactional
    public CreatedOrderResponse createOrder(CreateOrderRequest request) {

        IndividualCustomerResponse individualCustomerResponse = customerServiceClient.getIndividualCustomerById(request.getCustomerId());
        BillingAccountResponse billingAccountResponse = customerServiceClient.getBillingAccountById(request.getBillingAccountId());
        AddressResponse addressResponse = customerServiceClient.getAddressById(request.getAddressId());

        OrderCustomer orderCustomer = OrderCustomerMapper.INSTANCE.orderCustomerFromIndividualCustomer(individualCustomerResponse);
        OrderBillingAccount orderBillingAccount = OrderBillingAccountMapper.INSTANCE.orderBillingAccountFromBillingAccount(billingAccountResponse);
        OrderAddress orderAddress = OrderAddressMapper.INSTANCE.orderAddressFromAddress(addressResponse);
        orderCustomer.setOriginalCustomerId(individualCustomerResponse.getId());

        Order order = new Order();
        order.setAddressSnapshot(orderAddress);
        order.setBillingAccountSnapshot(orderBillingAccount);
        order.setCustomerSnapshot(orderCustomer);

        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal totalOrderAmount = BigDecimal.ZERO;

        // 3. ORKESTRASYON: Her kalem için Catalog Service'e git
        for (OrderItemRequest itemRequest : request.getItems()) {


            // a. Ürün Teklifini (Kampanya/Paket detayları) çek
            ProductOfferResponse offerResponse = catalogServiceClient.getProductOfferById(itemRequest.getProductOfferId());


            // c. OrderItem oluştur ve dönen Product ID'yi kaydet
            OrderItem orderItem = new OrderItem();
            orderItem.setProductOfferId(offerResponse.getId());
            orderItem.setPrice(offerResponse.getPrice());

            if (itemRequest.getCampaignOfferId() != 0) {
                orderItem.setCampaignProductOfferId(itemRequest.getCampaignOfferId());
                orderItem.setCampaignProductOfferName(itemRequest.getCampaignOfferName());
            }


            BigDecimal discountedPrice = offerResponse.getPrice();
            if(offerResponse.getDiscountRate() != null){
                discountedPrice = offerResponse.getPrice().multiply(BigDecimal.ONE.subtract(offerResponse.getDiscountRate()));
            }
            orderItem.setDiscountedPrice(discountedPrice);

            List<OrderItemCharacteristic> orderItemCharacteristicList = new ArrayList<>();
            if (itemRequest.getCharacteristics() != null) {
                for (ProductCharacteristicRequest requestItem : itemRequest.getCharacteristics()) {
                    OrderItemCharacteristic orderItemCharacteristic = new OrderItemCharacteristic();
                    orderItemCharacteristic.setCharacteristicId(requestItem.getCharacteristicId());
                    if (offerResponse.getGetListCharacteristicWithoutCharValResponseList() != null) {
                        String charName = offerResponse.getGetListCharacteristicWithoutCharValResponseList().stream()
                                .filter(c -> c.getId() == requestItem.getCharacteristicId()) // ID Eşleştirme
                                .findFirst()
                                .map(c -> c.getDescription()) // İsmi al
                                .orElse("Unknown Characteristic"); // Bulamazsa default
                        orderItemCharacteristic.setCharacteristicName(charName); // Mongo nesnesine set et
                    }
                    orderItemCharacteristic.setCharValueId(requestItem.getCharValueId());
                    orderItemCharacteristic.setValue(requestItem.getValue());
                    orderItemCharacteristicList.add(orderItemCharacteristic);
                }
            }

            orderItem.setCharacteristics(orderItemCharacteristicList);

            orderItems.add(orderItem);
            totalOrderAmount = totalOrderAmount.add(discountedPrice.multiply(BigDecimal.valueOf(itemRequest.getQuantity())));

            // b. Catalog Service'e "Ürün Yarat" emri ver (Stok düşer, product tablosuna yazar)
            CreateProductRequest createProductRequest = new CreateProductRequest();
            createProductRequest.setName(offerResponse.getName());
            createProductRequest.setPrice(offerResponse.getPrice());
            createProductRequest.setStock(itemRequest.getQuantity());
            createProductRequest.setProductOfferId(offerResponse.getId());

            List<CreateProdCharValueRequest> productCharValues = new ArrayList<>();
            if (itemRequest.getCharacteristics() != null) {
                for (ProductCharacteristicRequest reqItem : itemRequest.getCharacteristics()) {
                    CreateProdCharValueRequest charValueReq = new CreateProdCharValueRequest();

                    // ID'yi set et
                    charValueReq.setCharacteristicId(reqItem.getCharacteristicId());

                    // Değerleri set et (Seçmeli ID veya Manuel Value)
                    charValueReq.setCharValueId(reqItem.getCharValueId());
                    charValueReq.setValue(reqItem.getValue());

                    // Listeye ekle
                    productCharValues.add(charValueReq);
                }
            }
            createProductRequest.setProductCharValues(productCharValues);


            ProductResponse createdProduct = catalogServiceClient.createProduct(createProductRequest);


            orderItem.setProductId(createdProduct.getId());
            orderItem.setProductName(createdProduct.getName());
        }

        order.setItems(orderItems);
        order.setTotalAmount(totalOrderAmount);

        Order savedOrder = orderRepository.save(order);

        CreateOrderEvent orderCreatedEvent = new CreateOrderEvent(
                savedOrder.getId(),
                request.getBillingAccountId(),
                savedOrder.getCustomerSnapshot().getOriginalCustomerId()
        );
        orderCreatedProducer.produce(orderCreatedEvent);

        return new CreatedOrderResponse(savedOrder.getId(), savedOrder.getItems());
    }

    @Override
    public List<GetOrderResponse> getAllByCustomerId(String customerId) {
        // 1. Repository'den Customer ID'ye göre tüm siparişleri çek
        List<Order> orders = orderRepository.findByCustomerSnapshot_OriginalCustomerId(customerId);

        // 2. Mapper ile Response DTO'ya çevir
        return OrderMapper.INSTANCE.ordersToGetOrderResponses(orders);
    }
}

