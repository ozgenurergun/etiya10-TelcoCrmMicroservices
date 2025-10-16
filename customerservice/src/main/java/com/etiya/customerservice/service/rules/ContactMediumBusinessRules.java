package com.etiya.customerservice.service.rules;

import com.etiya.common.crosscuttingconcerns.exceptions.types.BusinessException;
import com.etiya.common.localization.LocalizationService;
import com.etiya.customerservice.domain.entities.ContactMedium;
import com.etiya.customerservice.repository.ContactMediumRepository;
import com.etiya.customerservice.service.messages.Messages;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ContactMediumBusinessRules {
    private final ContactMediumRepository contactMediumRepository;
    private final LocalizationService localizationService;


    public ContactMediumBusinessRules(ContactMediumRepository contactMediumRepository, LocalizationService localizationService) {
        this.contactMediumRepository = contactMediumRepository;
        this.localizationService = localizationService;
    }

    //Eğer eklenen veya güncellenen ContactMedium primary (birincil) olarak işaretlendiyse,
    //aynı müşteriye ait diğer tüm iletişim kayıtlarının isPrimary değerini false yaparak
    //sadece bir tanesinin primary kalmasını sağlar
    public void checkIsPrimaryOnlyOne(ContactMedium contactMedium) {
        if (contactMedium.isPrimary()) {
            UUID ownerCustomerId = contactMedium.getCustomer().getId();
            List<ContactMedium> contactMediumList = contactMediumRepository.findByCustomerId(ownerCustomerId);
            if (!contactMediumList.isEmpty()) {
                for (ContactMedium cm : contactMediumList) {
                    cm.setPrimary(false);
                }
            }
        }
    }

    //Bir ContactMedium silinmeden önce bu kaydın “primary” olup olmadığını kontrol et.
    //Eğer primary ise → silinmesine izin verme, hata fırlat.
    public void checkIsPrimary(ContactMedium contactMedium) {
        if (contactMedium.isPrimary()) {
            throw new BusinessException(localizationService.getMessage(Messages.CheckIsPrimary));
        }
    }
}

