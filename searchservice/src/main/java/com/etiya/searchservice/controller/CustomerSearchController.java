package com.etiya.searchservice.controller;

import com.etiya.searchservice.domain.CustomerSearch;
import com.etiya.searchservice.service.CustomerSearchService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer-search/")
public class CustomerSearchController {

    private final CustomerSearchService customerSearchService;

    public CustomerSearchController(CustomerSearchService customerSearchService) {
        this.customerSearchService = customerSearchService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerSearch> findAll(){
        return customerSearchService.findAll();
    }

    @GetMapping("fulltext")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerSearch> search(@RequestParam String keyword){
        return customerSearchService.searchAllFields(keyword);
    }

    @GetMapping("searchFirstname")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerSearch> searchByFirstName(@RequestParam String firstName){
        return customerSearchService.searchByFirstName(firstName);
    }

    @GetMapping("findNatId")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerSearch> findByNationalId(@RequestParam String nationalId){
        return customerSearchService.findByNationalId(nationalId);
    }

    @GetMapping("findLastNameFuzzy")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerSearch> findByLastNameFuzzy(@RequestParam String lastName) {
        return customerSearchService.findByLastNameFuzzy(lastName);
    }

    @GetMapping("firstAndLast")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerSearch> searchByFirstNameAndLastName(@RequestParam String firstName,@RequestParam String lastName) {
        return customerSearchService.searchByFirstNameAndLastName(firstName, lastName);
    }

    @GetMapping("findByCustomerId")
    @ResponseStatus(HttpStatus.OK)
    public CustomerSearch findByCustomerId(@RequestParam String customerId) {
        return customerSearchService.searchByCustomerId(customerId);
    }

    @GetMapping("search")
    public List<CustomerSearch> search(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String customerNumber,
            @RequestParam(required = false) String nationalId,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String value,
            @RequestParam(required = false) String orderId
    ) {
        return customerSearchService.searchDynamic(id, customerNumber, nationalId, firstName, lastName, value, orderId);
    }


}
