package com.etiya.customerservice.service.rules;

import com.etiya.common.crosscuttingconcerns.exceptions.types.BusinessException;
import com.etiya.common.localization.LocalizationService;
import com.etiya.customerservice.domain.entities.Address;
import com.etiya.customerservice.domain.entities.BillingAccount;
import com.etiya.customerservice.domain.enums.BillingAccountStatus;
import com.etiya.customerservice.domain.enums.BillingAccountType;
import com.etiya.customerservice.repository.AddressRepository;
import com.etiya.customerservice.repository.BillingAccountRepository;
import com.etiya.customerservice.service.abstracts.CustomerService;
import com.etiya.customerservice.service.messages.Messages;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BillingAccountBusinessRules {
    private final BillingAccountRepository billingAccountRepository;
    private final AddressRepository addressRepository;
    private final CustomerService customerService;  // ← EKLE!
    private final LocalizationService localizationService;



    public BillingAccountBusinessRules(BillingAccountRepository billingAccountRepository, AddressRepository addressRepository, CustomerService customerService, LocalizationService localizationService) {
        this.billingAccountRepository = billingAccountRepository;
        this.addressRepository = addressRepository;
        this.customerService = customerService;
        this.localizationService = localizationService;
    }

    //Verilen ID’ye sahip bir BillingAccount var mı kontrol eder.
    //Eğer varsa ve durumu aktifse (ACTIVE) silinmesine izin vermez, hata fırlatır.
    public void checkIfBillingAccountCanBeDeleted(int id){
        BillingAccount billingAccount = billingAccountRepository.findById(id).orElseThrow(() -> new RuntimeException(localizationService.getMessage(Messages.IdExists)));
        if(billingAccount.getStatus() == BillingAccountStatus.ACTIVE){
            throw new BusinessException(localizationService.getMessage(Messages.BillingAccountActive));
        }
    }

    //Amaç; var olan bir BillingAccountType değiştirilmesini engellemek.
    //Eğer mevcut hesap tipi ile yeni gönderilen tip farklıysa, bir BusinessException fırlat.
    public void checkIfTypeCanBeChanged(int id, BillingAccountType newType){
        BillingAccount existingBillingAccount = billingAccountRepository.findById(id).orElseThrow(() -> new RuntimeException(localizationService.getMessage(Messages.IdExists)));
        if(existingBillingAccount.getType() != newType){
            throw new BusinessException(localizationService.getMessage(Messages.ChangeBillingAccountType));
        }
    }

    //Amaç; verilen addressId’ye sahip adresin, belirtilen customerId’ye ait olup olmadığını kontrol etmek.
    //Eğer adres o müşteriye ait değilse, bir BusinessException fırlat.
    public void checkIfAddressBelongsToCustomer(int addressId, UUID customerId){
        Address address = addressRepository.findById(addressId).orElseThrow(() -> new BusinessException(localizationService.getMessage(Messages.IdExists)));
        if(address.getCustomer().getId() != customerId){
            throw new BusinessException(localizationService.getMessage(Messages.AddressBelongsToCustomer));
        }
    }

    //Kapalı durumdaki bir faturalandırma hesabının durumunu değiştiremezsiniz.
    public void checkIfStatusTransitionIsValid(int id, BillingAccountStatus newStatus){
        BillingAccount existingBillingAccount = billingAccountRepository.findById(id).orElseThrow(() -> new RuntimeException(localizationService.getMessage(Messages.IdExists)));
        BillingAccountStatus currentStatus = existingBillingAccount.getStatus();
        if(currentStatus == newStatus){
            return; // No change in status
        }
        if(currentStatus == BillingAccountStatus.CLOSED){
            throw new BusinessException(localizationService.getMessage(Messages.StatusTransitionIsValid));
        }
    }

    //Bir müşteri ile oluşturulmak istenen BillingAccount tipi uyumlu mu kontrol etmek.
    public void checkIfCustomerTypeMatchesAccountType(UUID customerId, BillingAccountType accountType) {
        String customerType = customerService.getCustomerType(customerId);
        if ((customerType.equals("INDIVIDUAL") && accountType != BillingAccountType.INDIVIDUAL) || (customerType.equals("CORPORATE") && accountType != BillingAccountType.CORPORATE)) {
            throw new BusinessException(localizationService.getMessage(Messages.CustomerTypeMatchesAccountType));
        }
    }

    //Faturalandırma hesabı açılmadan önce müşteri en az bir adrese sahip olmalı
    public void checkIfCustomerHasAddress(UUID customerId) {
        if (!addressRepository.existsByCustomerId(customerId)) {
            throw new BusinessException(localizationService.getMessage(Messages.CustomerHasAddress));
        }
    }
}

