package com.etiya.customerservice.transport.kafka.producer.customer;

import com.etiya.common.events.customer.CreateCustomerEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;


@Service
public class CreateCustomerProducer {
    //Bu sınıf CustomerService tarafında yer alıyor ve görevi yeni müşteri oluşturulduğunda event üretmek

    //Bu, Spring Kafka’nın sunduğu bir sınıf. Kafka’ya mesaj göndermek için kullanılır.
    //Buradaki <String, CreateCustomerEvent> kısmı:Key: String -- Value: CreateCustomerEvent olacağını belirtir.
    //private final KafkaTemplate<String, CreateCustomerEvent> kafkaTemplate;

    private final StreamBridge  streamBridge;
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateCustomerProducer.class);

    public CreateCustomerProducer(/*KafkaTemplate<String, CreateCustomerEvent> kafkaTemplate,*/ StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
        //this.kafkaTemplate = kafkaTemplate;
    }

    public void produceCustomerCreated(CreateCustomerEvent event){
        streamBridge.send("customerCreated-out-0", event);
        LOGGER.info(String.format("Customer created event => %s",event.customerId()));

        /* Message<CreateCustomerEvent> message = MessageBuilder.withPayload(event)
                .setHeader(KafkaHeaders.TOPIC,"create-customer").build();
        kafkaTemplate.send(message); */
    }

    //Yeni oluşturulan müşteri bilgileriyle bir CreateCustomerEvent nesnesi gelir.
    //(örneğin new CreateCustomerEvent("1", "CUST-001", "Ali", "Yılmaz", ...))
    //Bu event, bir Message objesine dönüştürülür.
    //Header kısmına "create-customer" topic’i yazılır.
    //kafkaTemplate.send(message) ile Kafka’ya gönderilir.

    //“Yeni müşteri oluşturuldu” bilgisini Kafka’ya gönder.
}

//StreamBridge, Spring Cloud Stream kütüphanesinin bir parçasıdır.
//Normalde Kafka mesajlarını göndermek için KafkaTemplate kullanılır.
//Ama StreamBridge daha soyut (abstraction) bir yapıdır; Kafka’ya doğrudan bağımlı kalmadan, event’leri göndermeni sağlar.
//KafkaTemplate → doğrudan Kafka’ya gönderir
//StreamBridge → “Kafka mı, RabbitMQ mu fark etmez, altyapı neyse oraya gönder”