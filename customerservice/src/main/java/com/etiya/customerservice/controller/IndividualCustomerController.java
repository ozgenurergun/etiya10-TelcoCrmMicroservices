package com.etiya.customerservice.controller;

import com.etiya.customerservice.service.abstracts.IndividualCustomerService;
import com.etiya.customerservice.service.requests.individualCustomer.CreateIndividualCustomerRequest;
import com.etiya.customerservice.service.requests.individualCustomer.UpdateIndividualCustomerRequest;
import com.etiya.customerservice.service.responses.individualCustomers.CreatedIndividualCustomerResponse;
import com.etiya.customerservice.service.responses.individualCustomers.GetIndividualCustomerResponse;
import com.etiya.customerservice.service.responses.individualCustomers.GetListIndividualCustomerResponse;
import com.etiya.customerservice.service.responses.individualCustomers.UpdatedIndividualCustomerResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController //api olduğu için
@RequestMapping("/api/individual-customers/") //bununla ilgili giden requesti neye maplediğimizi bildiriyoruz
//@CrossOrigin(origins = "http://localhost:4200")
public class IndividualCustomerController {
    private final IndividualCustomerService individualCustomerService;

    public IndividualCustomerController(IndividualCustomerService individualCustomerService) {
        this.individualCustomerService = individualCustomerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedIndividualCustomerResponse add(@Valid @RequestBody CreateIndividualCustomerRequest individualCustomer) {
        return individualCustomerService.add(individualCustomer);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdatedIndividualCustomerResponse update(@Valid @RequestBody UpdateIndividualCustomerRequest individualCustomer) {
        return individualCustomerService.update(individualCustomer);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetListIndividualCustomerResponse> getList(){
        return individualCustomerService.getList();
    }

    /*
    @GetMapping("getListWithAddresses")
    @ResponseStatus(HttpStatus.OK)
    public List<GetListIndividualCustomerResponse> getListWithAddresses(){
        return individualCustomerService.findAllWithAddresses();
    }*/

    @GetMapping("{lastName}")
    @ResponseStatus(HttpStatus.OK)
    public GetIndividualCustomerResponse getByLastName(@PathVariable String lastName) {
        return individualCustomerService.getByLastName(lastName);
    }

    @GetMapping("getByFirstNameStartingWith/{prefix}")
    @ResponseStatus(HttpStatus.OK)
    public List<GetListIndividualCustomerResponse> getByFirstNameStartingWith(@PathVariable String prefix){
        return individualCustomerService.getByFirstNameStartingWith(prefix);
    }

    @GetMapping("getByCustomerNumberPattern/{pattern}")
    @ResponseStatus(HttpStatus.OK)
    public List<GetListIndividualCustomerResponse> getByCustomerNumberPattern(@PathVariable String pattern){
        return individualCustomerService.getByCustomerNumberPattern(pattern);
    }

    @GetMapping("getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetIndividualCustomerResponse getById(@PathVariable String id){
        return individualCustomerService.getByCustomerId(id);
    }

    @DeleteMapping("delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable String id){
        individualCustomerService.delete(id);
    }

}