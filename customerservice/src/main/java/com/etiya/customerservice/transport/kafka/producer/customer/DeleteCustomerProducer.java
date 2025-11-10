package com.etiya.customerservice.transport.kafka.producer.customer;

import com.etiya.common.events.customer.CreateCustomerEvent;
import com.etiya.common.events.customer.DeleteCustomerEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class DeleteCustomerProducer {

    private final StreamBridge streamBridge;
    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteCustomerProducer.class);

    public DeleteCustomerProducer(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void produceCustomerDeleted(DeleteCustomerEvent event) {
        LOGGER.info(String.format("Sending Customer deleted event => %s", event.customerId()));

        // DEĞİŞİKLİK: Mesajı Partition Key ile gönder
        Message<DeleteCustomerEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.KEY, event.customerId()) // <-- ZORUNLU DEĞİŞİKLİK
                .build();

        // Config'de "createdEvents" olarak maplediğin binding'e gönder
        // (customerCreated-out-0 -> createdEvents'e gidiyor)
        streamBridge.send("customerDeleted-out-0", message);
    }
}
