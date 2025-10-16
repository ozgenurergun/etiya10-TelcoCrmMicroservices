package com.etiya.customerservice.service.rules;

import com.etiya.common.crosscuttingconcerns.exceptions.types.BusinessException;
import com.etiya.common.localization.LocalizationService;
import com.etiya.customerservice.domain.entities.Address;
import com.etiya.customerservice.repository.AddressRepository;
import com.etiya.customerservice.service.messages.Messages;
import org.apache.logging.log4j.message.Message;
import org.springframework.stereotype.Service;

@Service
public class AddressBusinessRules {
    private final AddressRepository addressRepository;
    private final LocalizationService localizationService;

    public AddressBusinessRules(AddressRepository addressRepository, LocalizationService localizationService) {
        this.addressRepository = addressRepository;
        this.localizationService = localizationService;
    }

    //Bir adrese bağlı herhangi bir BillingAccount olup olmadığını kontrol eder.
    public void checkIfBillingAccountExists(int id) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new BusinessException(localizationService.getMessage(Messages.IdExists)));
        if (!address.billingAccounts.isEmpty()) {
            throw new BusinessException(localizationService.getMessage(Messages.BillingAccountExists));
        }
    }

//    public void checkIfAddressExists(int id) {
//        if(addressRepository.existsById(id)){
//            throw new BusinessException("Address with id " + id + " doesnt exists");
//        }
//    }
}