package com.etiya.searchservice.transport.kafka.customer.consumer;

import com.etiya.common.events.customer.CreateCustomerEvent;
import com.etiya.searchservice.domain.CustomerSearch;
import com.etiya.searchservice.service.CustomerSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

//Bu sınıf SearchService tarafında yer alıyor.
//Görevi, Kafka’dan gelen event’leri tüketmek (consume etmek) ve Elasticsearch gibi bir arama motoruna kaydetmek.
@Configuration
public class CreatedCustomerConsumer {

    private final CustomerSearchService customerSearchService;
    private final Logger LOGGER = LoggerFactory.getLogger(CreatedCustomerConsumer.class);

    public CreatedCustomerConsumer(CustomerSearchService customerSearchService) {
        this.customerSearchService = customerSearchService;
    }

    @Bean
    public Consumer<CreateCustomerEvent> customerCreated(){
        return event -> {
            CustomerSearch customerSearch = new CustomerSearch(event.customerId(),
                    event.customerNumber(),
                    event.firstName(), event.middleName(),
                    event.lastName(), event.nationalId(), event.dateOfBirth(), event.fatherName(), event.motherName(), event.gender());
            customerSearchService.add(customerSearch);
            LOGGER.info(String.format("Consumed Customer Event: %s", event.customerId()));
        };
    }



    //topics = "create-customer" → hangi topic’i dinleyeceğini belirtir.
    //groupId → consumer’ların aynı grupta çalışabilmesi için verilen isimdir.
    /* @KafkaListener(topics = "create-customer",groupId = "create-customer-group") //Bu annotation, bu metodu Kafka topic’ine bağlar.
    public void consume(CreateCustomerEvent event){
        LOGGER.info(String.format("Consumed Customer Event: %s", event.customerId()));
        //Log atılır → event başarıyla dinlenmiş demektir.
        CustomerSearch customerSearch = new CustomerSearch(event.customerId(),
                event.customerNumber(),
                event.firstName(),
                event.lastName(),
                event.nationalId(),
                event.dateOfBirth(),
                event.motherName(),
                event.fatherName(),
                event.gender());
        //Yani gelen CreateCustomerEvent verisi, SearchService’in kendi veri modeline (CustomerSearch) dönüştürülüyor.
        customerSearchService.add(customerSearch);
        //Bu satırda event’ten elde edilen müşteri verisi SearchService’in veri tabanına (örneğin MongoDB veya Elasticsearch) kaydediliyor.
        //Yani artık müşteri arama indeksinde de yer alıyor.
    } */

    //Kafka tarafında "create-customer" topic’ine bir mesaj gönderildiğinde, bu metot otomatik olarak tetiklenir.
}
