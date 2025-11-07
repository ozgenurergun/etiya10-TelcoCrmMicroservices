package com.etiya.searchservice.transport.kafka.contactMedium.consumer;

import com.etiya.common.events.address.DeleteAddressEvent;
import com.etiya.common.events.contactMedium.DeleteContactMediumEvent;
import com.etiya.searchservice.domain.ContactMedium;
import com.etiya.searchservice.domain.CustomerSearch;
import com.etiya.searchservice.service.CustomerSearchService;
import com.etiya.searchservice.transport.kafka.address.consumer.DeletedAddressConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;
import java.util.function.Consumer;

@Configuration
public class DeleteContactMediumConsumer {

    private final CustomerSearchService customerSearchService;
    private final Logger LOGGER = LoggerFactory.getLogger(DeleteContactMediumConsumer.class);

    public DeleteContactMediumConsumer(CustomerSearchService customerSearchService) {
        this.customerSearchService = customerSearchService;
    }

    @Bean
    public Consumer<DeleteContactMediumEvent> contactmediumDeleted() {
        return event -> {
            // 1. GET: Parent dokümanı (CustomerSearch) bul
            Optional<CustomerSearch> customerSearchOptional =
                    customerSearchService.findById(event.customerId().toString());

            if (customerSearchOptional.isEmpty()) {
                LOGGER.warn("Delete event received for non-existent customer in ES. CustomerID: {}", event.customerId());
                return; // Müşteri yoksa işlem yapma
            }

            CustomerSearch customerSearch = customerSearchOptional.get();

            // 2. UPDATE: Listeyi al ve ilgili ID'yi çıkar
            if (customerSearch.getContactMediums() != null && !customerSearch.getContactMediums().isEmpty()) {

                // Listeden event.id() ile eşleşen ContactMedium'u sil
                boolean removed = customerSearch.getContactMediums()
                        .removeIf(cm -> cm.getId() == (event.id())); // <-- Sihir burada

                if (removed) {
                    // 3. SAVE: Dokümanın tamamını geri kaydet
                    customerSearchService.save(customerSearch);
                    LOGGER.info(String.format("Deleted contact medium [ID: %s] from CustomerSearch [ID: %s]",
                            event.id(), event.customerId()));
                } else {
                    LOGGER.warn("Tried to delete non-existent contact medium [ID: %s] from CustomerSearch [ID: %s]",
                            event.id(), event.customerId());
                }
            } else {
                LOGGER.warn("Contact medium list is empty or null for CustomerSearch [ID: %s]. No deletion possible.",
                        event.customerId());
            }
        };
    }

//    @Bean
//    public Consumer<DeleteContactMediumEvent> contactmediumDeleted(){
//        return event -> {
//            ContactMedium contactMedium = new ContactMedium(
//                    event.id(),
//                    event.customerId().toString()
//            );
//            customerSearchService.deleteContactMedium(contactMedium);
//            LOGGER.info(String.format("Deleted Customer => %s", event.id()));
//        };
//    }
}
