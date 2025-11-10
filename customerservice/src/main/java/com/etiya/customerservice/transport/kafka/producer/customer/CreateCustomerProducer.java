package com.etiya.customerservice.transport.kafka.producer.customer;

import com.etiya.common.events.customer.CreateCustomerEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.support.KafkaHeaders; // BUNU EKLE
import org.springframework.messaging.Message; // BUNU EKLE
import org.springframework.messaging.support.MessageBuilder; // BUNU EKLE
import org.springframework.stereotype.Service;

@Service
public class CreateCustomerProducer {

    private final StreamBridge  streamBridge;
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateCustomerProducer.class);

    public CreateCustomerProducer(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void produceCustomerCreated(CreateCustomerEvent event) {
        LOGGER.info(String.format("Sending Customer created event => %s", event.customerId()));

        // DEĞİŞİKLİK: Mesajı Partition Key ile gönder
        Message<CreateCustomerEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.KEY, event.customerId()) // <-- ZORUNLU DEĞİŞİKLİK
                .build();

        // Config'de "createdEvents" olarak maplediğin binding'e gönder
        // (customerCreated-out-0 -> createdEvents'e gidiyor)
        streamBridge.send("customerCreated-out-0", message);
    }
}

//StreamBridge, Spring Cloud Stream kütüphanesinin bir parçasıdır.
//Normalde Kafka mesajlarını göndermek için KafkaTemplate kullanılır.
//Ama StreamBridge daha soyut (abstraction) bir yapıdır; Kafka’ya doğrudan bağımlı kalmadan, event’leri göndermeni sağlar.
//KafkaTemplate → doğrudan Kafka’ya gönderir
//StreamBridge → “Kafka mı, RabbitMQ mu fark etmez, altyapı neyse oraya gönder”