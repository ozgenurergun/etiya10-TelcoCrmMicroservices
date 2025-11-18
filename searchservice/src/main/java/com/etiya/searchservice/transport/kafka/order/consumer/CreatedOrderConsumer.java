package com.etiya.searchservice.transport.kafka.order.consumer;

import com.etiya.common.events.address.CreateAddressEvent;
import com.etiya.common.events.order.CreateOrderEvent;
import com.etiya.searchservice.domain.Address;
import com.etiya.searchservice.service.CustomerSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class CreatedOrderConsumer {

    private final CustomerSearchService customerSearchService;
    private final Logger LOGGER = LoggerFactory.getLogger(CreatedOrderConsumer.class);

    public CreatedOrderConsumer(CustomerSearchService customerSearchService) {
        this.customerSearchService = customerSearchService;
    }

    @Bean
    public Consumer<CreateOrderEvent> orderCreated(){
        return event -> {
            customerSearchService.addOrder(event.customerId(),event.orderId());
            LOGGER.info(String.format("Consumed Order => %s", event.orderId()));
        };
    }
}
