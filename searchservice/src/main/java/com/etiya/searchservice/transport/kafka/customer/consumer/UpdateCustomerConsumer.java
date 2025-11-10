package com.etiya.searchservice.transport.kafka.customer.consumer;

import com.etiya.common.events.address.UpdateAddressEvent;
import com.etiya.common.events.customer.UpdateCustomerEvent;
import com.etiya.searchservice.domain.Address;
import com.etiya.searchservice.domain.CustomerSearch;
import com.etiya.searchservice.service.CustomerSearchService;
import com.etiya.searchservice.transport.kafka.address.consumer.UpdatedAddressConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class UpdateCustomerConsumer {
    private final CustomerSearchService customerSearchService;
    private final Logger LOGGER = LoggerFactory.getLogger(UpdateCustomerConsumer.class);

    public UpdateCustomerConsumer(CustomerSearchService customerSearchService) {
        this.customerSearchService = customerSearchService;
    }

    @Bean
    public Consumer<UpdateCustomerEvent> customerUpdated(){
        return event -> {
            CustomerSearch customerSearch = new CustomerSearch(
                    event.customerId(),
                    event.customerNumber(),
                    event.firstName(),
                    event.middleName(),
                    event.lastName(),
                    event.nationalId(),
                    event.dateOfBirth().toString(),
                    event.fatherName(),
                    event.motherName(),
                    event.gender()
            );
            customerSearchService.updateCustomer(customerSearch);
            LOGGER.info(String.format("Updated Customer => %s", event.customerId()));
        };
    }
}
