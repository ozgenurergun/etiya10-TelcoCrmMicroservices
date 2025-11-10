package com.etiya.searchservice.transport.kafka.customer.consumer;

import com.etiya.common.events.address.DeleteAddressEvent;
import com.etiya.common.events.customer.DeleteCustomerEvent;
import com.etiya.searchservice.domain.Address;
import com.etiya.searchservice.domain.CustomerSearch;
import com.etiya.searchservice.service.CustomerSearchService;
import com.etiya.searchservice.transport.kafka.address.consumer.DeletedAddressConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class DeleteCustomerConsumer {
    private final CustomerSearchService customerSearchService;
    private final Logger LOGGER = LoggerFactory.getLogger(DeleteCustomerConsumer.class);

    public DeleteCustomerConsumer(CustomerSearchService customerSearchService) {
        this.customerSearchService = customerSearchService;
    }

    @Bean
    public Consumer<DeleteCustomerEvent> customerDeleted(){
        return event -> {
            CustomerSearch customerSearch = new CustomerSearch(
                    event.customerId()
            );
            customerSearchService.deleteCustomer(customerSearch);
            LOGGER.info(String.format("Deleted Customer => %s", event.customerId()));
        };
    }
}
