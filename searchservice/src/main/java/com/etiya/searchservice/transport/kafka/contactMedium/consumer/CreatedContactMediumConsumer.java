package com.etiya.searchservice.transport.kafka.contactMedium.consumer;

import com.etiya.common.events.address.CreateAddressEvent;
import com.etiya.common.events.contactMedium.CreateContactMediumEvent;
import com.etiya.searchservice.domain.ContactMedium;
import com.etiya.searchservice.domain.CustomerSearch;
import com.etiya.searchservice.service.CustomerSearchService;
import com.etiya.searchservice.transport.kafka.customer.consumer.CreatedCustomerConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Consumer;

@Configuration
public class CreatedContactMediumConsumer {

    private final CustomerSearchService customerSearchService;
    private final Logger LOGGER = LoggerFactory.getLogger(CreatedContactMediumConsumer.class);

    public CreatedContactMediumConsumer(CustomerSearchService customerSearchService) {
        this.customerSearchService = customerSearchService;
    }

    @Bean
    public Consumer<CreateContactMediumEvent> contactmediumCreated(){
        return event -> {

            // 1. GET: İlgili CustomerSearch'ü ES'ten bul
            Optional<CustomerSearch> customerSearchOptional = customerSearchService.findById(event.customerId().toString());

            if (customerSearchOptional.isEmpty()) {
                // Bu kritik bir durum. Müşteri ana veritabanında var ama ES'te yok (henüz).
                LOGGER.warn("Contact medium event received for non-existent customer in ES. CustomerID: {}", event.customerId());
                return; // Yapacak bir şey yok.
            }

            CustomerSearch customerSearch = customerSearchOptional.get();

            // 2. UPDATE: Yeni ContactMedium nesnesini oluştur
            // NOT: Bu ContactMedium, senin ES'teki 'nested' objeni temsil etmeli.
            // O objenin içinde 'customerId'ye artık gerek yok, çünkü zaten 'parent' dokümanın içinde.
            // Constructor'ını buna göre düzeltmen gerekebilir.
            ContactMedium newContactMedium = new ContactMedium(
                    event.id(),
                    event.type(),
                    event.value(),
                    event.isPrimary()
                    // event.customerId().toString()  <-- BUNA ARTIK GEREK YOK
            );

            // 3. UPDATE: Listeyi güncelle (NullPointerException'a karşı)
            if (customerSearch.getContactMediums() == null) {
                customerSearch.setContactMediums(new ArrayList<>());
            }

            // Listeye yeni objeyi ekle
            customerSearch.getContactMediums().add(newContactMedium);

            // 4. SAVE: Dokümanın tamamını ES'e geri kaydet
            customerSearchService.save(customerSearch);

            LOGGER.info(String.format("Added new contact medium to CustomerSearch [ID: %s]", event.customerId()));
        };
    }


//    @Bean
//    public Consumer<CreateContactMediumEvent> contactmediumCreated(){
//        return event -> {
//            ContactMedium contactMedium = new ContactMedium(
//                    event.id(),
//                    event.type(),
//                    event.value(),
//                    event.isPrimary(),
//                    event.customerId().toString()
//            );
//            customerSearchService.addContactMedium(contactMedium);
//            LOGGER.info(String.format("Created contact medium => %s", event.id()));
//        };
//    }


    /*
    @KafkaListener(topics = "create-contact-medium", groupId = "create-contact-medium-group")
    public void consume(CreateContactMediumEvent event){
        LOGGER.info(String.format("Created contact medium => %s", event.id()));
        ContactMedium contactMedium = new ContactMedium(
                event.id(),
                event.type(),
                event.value(),
                event.isPrimary()
        );
        customerSearchService.addContactMedium(contactMedium, event.customerId().toString());
    }

     */
}
