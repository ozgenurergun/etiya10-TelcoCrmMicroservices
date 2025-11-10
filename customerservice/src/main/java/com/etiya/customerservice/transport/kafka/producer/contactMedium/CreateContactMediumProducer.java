package com.etiya.customerservice.transport.kafka.producer.contactMedium;

import com.etiya.common.events.contactMedium.CreateContactMediumEvent;
import com.etiya.customerservice.transport.kafka.producer.address.CreateAddressProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.support.KafkaHeaders; // BUNU EKLE
import org.springframework.messaging.Message; // BUNU EKLE
import org.springframework.messaging.support.MessageBuilder; // BUNU EKLE
import org.springframework.stereotype.Service;

@Service
public class CreateContactMediumProducer {

    private final StreamBridge streamBridge;
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateAddressProducer.class);

    public CreateContactMediumProducer(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void produceContactMediumCreated(CreateContactMediumEvent event){
        LOGGER.info(String.format("Sending ContactMedium created event => %s", event.customerId()));

        // DEĞİŞİKLİK: Mesajı Partition Key ile gönder
        Message<CreateContactMediumEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.KEY, event.customerId().toString()) // <-- ZORUNLU DEĞİŞİKLİK
                .build();

        // (contactmediumCreated-out-0 -> createdEvents'e gidiyor)
        streamBridge.send("contactmediumCreated-out-0", message);
    }
}