package com.etiya.searchservice.transport.kafka.contactMedium.consumer;

import com.etiya.common.events.address.UpdateAddressEvent;
import com.etiya.common.events.contactMedium.UpdateContactMediumEvent;
import com.etiya.searchservice.domain.Address;
import com.etiya.searchservice.domain.ContactMedium;
import com.etiya.searchservice.domain.CustomerSearch;
import com.etiya.searchservice.service.CustomerSearchService;
import com.etiya.searchservice.transport.kafka.address.consumer.UpdatedAddressConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;
import java.util.function.Consumer;

@Configuration
public class UpdateContactMediumConsumer {
    private final CustomerSearchService customerSearchService;
    private final Logger LOGGER = LoggerFactory.getLogger(UpdateContactMediumConsumer.class);

    public UpdateContactMediumConsumer(CustomerSearchService customerSearchService) {
        this.customerSearchService = customerSearchService;
    }

    @Bean
    public Consumer<UpdateContactMediumEvent> contactmediumUpdated() {
        return event -> {
            // 1. GET: Parent dokümanı (CustomerSearch) bul
            Optional<CustomerSearch> customerSearchOptional =
                    customerSearchService.findById(event.customerId().toString());

            if (customerSearchOptional.isEmpty()) {
                LOGGER.warn("Update event received for non-existent customer in ES. CustomerID: {}", event.customerId());
                return;
            }

            CustomerSearch customerSearch = customerSearchOptional.get();

            // 2. UPDATE: Listeyi al ve ilgili objeyi bul
            if (customerSearch.getContactMediums() != null && !customerSearch.getContactMediums().isEmpty()) {

                Optional<ContactMedium> cmOptional = customerSearch.getContactMediums().stream()
                        .filter(cm -> cm.getId() == (event.id())) // <-- Aranan objeyi bul
                        .findFirst();

                if (cmOptional.isPresent()) {
                    ContactMedium cmToUpdate = cmOptional.get();

                    // --- Business Logic: isPrimary kuralı ---
                    // Eğer bu 'true' olarak ayarlanıyorsa, diğerlerini 'false' yap
                    if (event.isPrimary()) {
                        customerSearch.getContactMediums().forEach(cm -> cm.setPrimary(false));
                    }
                    // ------------------------------------------

                    // Objeyi yeni verilerle güncelle (referans üzerinden liste güncellenir)
                    cmToUpdate.setType(event.type());
                    cmToUpdate.setValue(event.value());
                    cmToUpdate.setPrimary(event.isPrimary());

                    // 3. SAVE: Dokümanın tamamını geri kaydet
                    customerSearchService.save(customerSearch);
                    LOGGER.info(String.format("Updated contact medium [ID: %s] in CustomerSearch [ID: %s]",
                            event.id(), event.customerId()));
                } else {
                    LOGGER.warn("Tried to update non-existent contact medium [ID: %s] in CustomerSearch [ID: %s]",
                            event.id(), event.customerId());
                }
            } else {
                LOGGER.warn("Contact medium list is empty or null for CustomerSearch [ID: %s]. No update possible.",
                        event.customerId());
            }
        };
    }

//    @Bean
//    public Consumer<UpdateContactMediumEvent> contactmediumUpdated(){
//        return event -> {
//            ContactMedium contactMedium = new ContactMedium(
//                    event.id(),
//                    event.type(),
//                    event.value(),
//                    event.isPrimary(),
//                    event.customerId().toString()
//            );
//            customerSearchService.updateContactMedium(contactMedium);
//            LOGGER.info(String.format("Updated Contact Medium => %s", event.id()));
//        };
//    }
}
