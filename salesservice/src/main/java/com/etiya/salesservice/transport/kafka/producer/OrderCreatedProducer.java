package com.etiya.salesservice.transport.kafka.producer;

import com.etiya.common.events.order.CreateOrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class OrderCreatedProducer {

    private final StreamBridge streamBridge;
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderCreatedProducer.class);

    public OrderCreatedProducer(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void produce(CreateOrderEvent event) {
        Message<CreateOrderEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.KEY, String.valueOf(event.billingAccountId())) // Key olarak accountId
                .build();

        // "orderCreated-out-0" bağlamını config dosyasında tanımlayacağız
        streamBridge.send("orderCreated-out-0", message);
        LOGGER.info("OrderCreatedEvent produced for BillingAccount: " + event.billingAccountId());
    }
}
