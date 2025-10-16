package com.etiya.customerservice.service.rules;

import com.etiya.common.crosscuttingconcerns.exceptions.types.BusinessException;
import com.etiya.common.localization.LocalizationService;
import com.etiya.customerservice.domain.entities.CorporateCustomer;
import com.etiya.customerservice.repository.CorporateCustomerRepository;
import com.etiya.customerservice.service.messages.Messages;
import org.springframework.stereotype.Service;

@Service
public class CorporateCustomerBusinessRules extends CustomerBusinessRules<CorporateCustomer> {

    private final CorporateCustomerRepository corporateCustomerRepository;
    private final LocalizationService localizationService;

    public CorporateCustomerBusinessRules(CorporateCustomerRepository corporateCustomerRepository, LocalizationService localizationService) {
        super(corporateCustomerRepository);
        this.corporateCustomerRepository = corporateCustomerRepository;
        this.localizationService = localizationService;
    }

    //Verilen vergi numarasına sahip bir CorporateCustomer zaten var mı kontrolü
    public void checkIfCorporateCustomerExistsByTaxNumber(String taxNumber) {
        if(corporateCustomerRepository.existsByTaxNumber(taxNumber)) {
            throw new BusinessException(localizationService.getMessage(Messages.TaxNumberExists));
        }
    }
}
