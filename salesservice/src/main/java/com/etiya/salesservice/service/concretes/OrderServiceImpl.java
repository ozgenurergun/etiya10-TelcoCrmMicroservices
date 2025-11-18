package com.etiya.salesservice.service.concretes;

import com.etiya.common.responses.ProductOfferResponse;
import com.etiya.common.responses.ProductResponse;
import com.etiya.salesservice.client.CatalogServiceClient;
import com.etiya.salesservice.client.CustomerServiceClient;
import com.etiya.salesservice.domain.Order;
import com.etiya.salesservice.domain.OrderItem;
import com.etiya.salesservice.repository.OrderRepository;
import com.etiya.salesservice.service.abstracts.OrderService;
import com.etiya.salesservice.service.dtos.requests.CreateOrderRequest;
import com.etiya.salesservice.service.dtos.requests.CreateProductRequest;
import com.etiya.salesservice.service.dtos.requests.OrderItemRequest;
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

    public OrderServiceImpl(OrderRepository orderRepository, CustomerServiceClient customerServiceClient, CatalogServiceClient catalogServiceClient) {
        this.orderRepository = orderRepository;
        this.customerServiceClient = customerServiceClient;
        this.catalogServiceClient = catalogServiceClient;
    }

    @Override
    @Transactional
    public void createOrder(CreateOrderRequest request) {
        // 1. VALIDASYON: Customer Service
        try {
            customerServiceClient.getAddressById(request.getAddressId()); // Adres var mı?
            customerServiceClient.getBillingAccountById(request.getBillingAccountId()); // Hesap var mı?
        } catch (Exception e) {
            throw new RuntimeException("Validasyon hatası: Müşteri, Adres veya Hesap bilgileri doğrulanamadı.");
        }

        // 2. HAZIRLIK: Sipariş oluştur
        Order order = new Order();
        order.setCustomerId(request.getCustomerId());
        order.setAddressId(request.getAddressId());
        order.setBillingAccountId(request.getBillingAccountId());

        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal totalOrderAmount = BigDecimal.ZERO;

        // 3. ORKESTRASYON: Her kalem için Catalog Service'e git
        for (OrderItemRequest itemRequest : request.getItems()) {
            // a. Ürün Teklifini (Kampanya/Paket detayları) çek
            ProductOfferResponse offerResponse = catalogServiceClient.getProductOfferById(itemRequest.getProductOfferId());

            // b. Catalog Service'e "Ürün Yarat" emri ver (Stok düşer, product tablosuna yazar)
            CreateProductRequest createProductRequest = new CreateProductRequest();
            createProductRequest.setName(offerResponse.getName());
            createProductRequest.setPrice(offerResponse.getPrice());
            createProductRequest.setStock(1); // 1 adet satıldı
            createProductRequest.setProductOfferId(offerResponse.getId());
            createProductRequest.setCatalogId(1); // Default katalog

            ProductResponse createdProduct = catalogServiceClient.createProduct(createProductRequest);

            // c. OrderItem oluştur ve dönen Product ID'yi kaydet
            OrderItem orderItem = new OrderItem();
            orderItem.setProductOfferId(offerResponse.getId());
            orderItem.setPrice(offerResponse.getPrice());
            orderItem.setProductId(createdProduct.getId()); // BU ID ÇOK ÖNEMLİ!

            orderItems.add(orderItem);
            totalOrderAmount = totalOrderAmount.add(offerResponse.getPrice().multiply(BigDecimal.valueOf(itemRequest.getQuantity())));
        }

        order.setTotalAmount(totalOrderAmount);

        // 4. KAYIT: Mongo'ya yaz
        orderRepository.save(order);
    }
}

