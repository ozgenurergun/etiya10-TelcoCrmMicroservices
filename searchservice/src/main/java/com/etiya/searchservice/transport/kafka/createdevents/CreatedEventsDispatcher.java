package com.etiya.searchservice.transport.kafka.createdevents;

import com.etiya.common.events.address.CreateAddressEvent;
import com.etiya.common.events.contactMedium.CreateContactMediumEvent;
import com.etiya.common.events.customer.CreateCustomerEvent;
import com.etiya.searchservice.domain.Address;
import com.etiya.searchservice.domain.ContactMedium;
import com.etiya.searchservice.domain.CustomerSearch;
import com.etiya.searchservice.service.CustomerSearchService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Consumer;

@Configuration
public class CreatedEventsDispatcher {

    private static final Logger log = LoggerFactory.getLogger(CreatedEventsDispatcher.class);
    private final CustomerSearchService customerSearchService;
    private final ObjectMapper objectMapper = new ObjectMapper(); // Manuel JSON parse için

    public CreatedEventsDispatcher(CustomerSearchService customerSearchService) {
        this.customerSearchService = customerSearchService;
    }

    // Bean adı 'createdEvents' olmalı (config ile aynı)
    // 'byte[]' tüketir (useNativeDecoding=false sayesinde)
    @Bean
    public Consumer<byte[]> createdEvents() {
        return payload -> {
            String json = new String(payload, StandardCharsets.UTF_8);
            try {
                // 1. JSON'u genel bir Map'e çevir (Tipini anlamak için)
                Map<String, Object> eventMap = objectMapper.readValue(json,
                        new TypeReference<Map<String, Object>>() {});

                // 2. Tipi belirlemek için event'lerin içindeki benzersiz alanları kullan
                if (eventMap.containsKey("nationalId") && eventMap.containsKey("firstName")) {
                    // BU BİR MÜŞTERİDİR
                    log.info("Handling CreateCustomerEvent...");
                    CreateCustomerEvent e = objectMapper.readValue(json, CreateCustomerEvent.class);
                    CustomerSearch customerSearch = new CustomerSearch(
                            e.customerId(), e.customerNumber(), e.firstName(), e.middleName(),
                            e.lastName(), e.nationalId(), e.dateOfBirth(),
                            e.fatherName(), e.motherName(), e.gender()
                    );
                    customerSearch.setAddresses(new ArrayList<>()); // Listeleri boş başlat
                    customerSearch.setContactMediums(new ArrayList<>());
                    customerSearchService.add(customerSearch); // 'add' metodu DOKÜMANI YARATIR

                } else if (eventMap.containsKey("addressId") && eventMap.containsKey("street")) {
                    // BU BİR ADRESTİR
                    log.info("Handling CreateAddressEvent...");
                    CreateAddressEvent e = objectMapper.readValue(json, CreateAddressEvent.class);
                    // Partition Key sayesinde sıralama garanti, Müşteri %100 var.
                    // 'addAddress' metodu (find + update + save yapar)
                    customerSearchService.addAddress(new Address(
                            e.addressId(), e.street(), e.houseNumber(), e.description(),
                            e.isDefault(), e.districtId(), e.cityName(), e.customerId()
                    ));

                } else if (eventMap.containsKey("type") && eventMap.containsKey("value") && eventMap.containsKey("customerId")) {
                    // BU BİR İLETİŞİM BİLGİSİDİR
                    log.info("Handling CreateContactMediumEvent...");
                    CreateContactMediumEvent e = objectMapper.readValue(json, CreateContactMediumEvent.class);
                    // Partition Key sayesinde sıralama garanti, Müşteri %100 var.
                    // 'addContactMedium' metodu (find + update + save yapar)
                    customerSearchService.addContactMedium(new ContactMedium(
                            e.id(), e.type(), e.value(), e.isPrimary(), e.customerId().toString()
                    ));
                } else {
                    log.warn("Bilinmeyen event yapısı: {}", json);
                }
            } catch (Exception e) {
                log.error("Event işlenirken hata (JSON: {}): {}. Yeniden denenecek.", json, e.getMessage(), e);
                // Hata fırlat ki bu partition'daki mesajlar yeniden denensin.
                throw new RuntimeException("Event işleme hatası, yeniden denenecek.", e);
            }
        };
    }
}