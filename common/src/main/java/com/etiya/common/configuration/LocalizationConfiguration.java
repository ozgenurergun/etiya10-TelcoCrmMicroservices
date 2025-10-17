package com.etiya.common.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

@Configuration //Bu sınıf bir Spring configuration (yapılandırma) sınıfı olduğunu belirtir.
//Yani içindeki @Bean metotları Spring konteynerine bean olarak eklenir
public class LocalizationConfiguration {

    @Bean //Bu bean, çok dilli mesaj dosyalarının (messages.properties) okunmasını sağlar.
    public ResourceBundleMessageSource bundleMessageSource(){
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }

    //Bu metot, uygulamada hangi dili (locale) kullanacağını belirler.
    @Bean
    public LocaleResolver localeResolver(){
        //hangi lang seçtiysen ona setler.
        AcceptHeaderLocaleResolver acceptHeaderLocaleResolver = new AcceptHeaderLocaleResolver();
        acceptHeaderLocaleResolver.setDefaultLocale(new Locale("tr")); //Eğer istek başlığında dil belirtilmemişse, varsayılan olarak Türkçe kullanılır.
        return acceptHeaderLocaleResolver;
        //LocaleResolver: Kullanıcının tercih ettiği dili çözümleyen bir arayüzdür.
        //AcceptHeaderLocaleResolver: Kullanıcının HTTP isteğindeki Accept-Language başlığına bakar.
    }

    @Bean //Bu bean, Spring Validation altyapısının (örneğin @NotNull, @Size, @Email) mesajlarını çok dillileştirir.
    @Primary //Birden fazla validator tanımlıysa, bu bean’in öncelikli (primary) olmasını sağlar.
    public LocalValidatorFactoryBean localValidatorFactoryBean(){
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(bundleMessageSource());
        return bean;
    }

}
