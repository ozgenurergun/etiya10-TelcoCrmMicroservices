package com.etiya.customerservice.transport.kafka.producer.address;

import com.etiya.common.events.address.CreateAddressEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.support.KafkaHeaders; // BUNU EKLE
import org.springframework.messaging.Message; // BUNU EKLE
import org.springframework.messaging.support.MessageBuilder; // BUNU EKLE
import org.springframework.stereotype.Service;

@Service
public class CreateAddressProducer {

    private final StreamBridge streamBridge;
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateAddressProducer.class);

    public CreateAddressProducer(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void produceAddressCreated(CreateAddressEvent event){
        LOGGER.info(String.format("Sending Address created event => %s", event.addressId()));

        // DEĞİŞİKLİK: Mesajı Partition Key ile gönder
        Message<CreateAddressEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.KEY, event.customerId()) // <-- ZORUNLU DEĞİŞİKLİK
                .build();

        // (addressCreated-out-0 -> createdEvents'e gidiyor)
        streamBridge.send("addressCreated-out-0", message);
    }
}