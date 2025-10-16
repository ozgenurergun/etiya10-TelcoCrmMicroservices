package com.etiya.common.localization;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LocalizationServiceImpl implements LocalizationService{

    //MessageSource → Spring’in mesaj kaynağı (properties dosyası) için kullanılan arayüzü.
    private final MessageSource messageSource;

    public LocalizationServiceImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public String getMessage(String key) {
        return messageSource.getMessage(key,null, LocaleContextHolder.getLocale());
        //key → mesaj anahtarı (ör. NationalityIdentityExists)
        //null → mesaj parametresi yok (mesaj {0} gibi placeholder’lar kullanıyorsan burada geçersin)
        //LocaleContextHolder.getLocale() → aktif kullanıcı dilini alır (ör. Türkçe veya İngilizce)
    }
}
