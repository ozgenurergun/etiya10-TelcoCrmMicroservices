package com.etiya.basketservice.transport.kafka.consumer;

import com.etiya.basketservice.service.abstracts.CartService;
import com.etiya.common.events.order.CreateOrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class OrderCreatedConsumer {

    private final CartService cartService;
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderCreatedConsumer.class);

    public OrderCreatedConsumer(CartService cartService) {
        this.cartService = cartService;
    }

    @Bean
    public Consumer<CreateOrderEvent> orderCreated() {
        return event -> {
            LOGGER.info("OrderCreatedEvent received. Deleting cart for BillingAccount: " + event.billingAccountId());

            // Sepeti silme işlemi
            cartService.deleteCart(event.billingAccountId());
        };
    }
}
