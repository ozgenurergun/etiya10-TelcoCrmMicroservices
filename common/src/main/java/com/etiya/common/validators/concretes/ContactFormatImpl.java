package com.etiya.common.validators.concretes;

import com.etiya.common.validators.annotations.ContactFormat;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;


public class ContactFormatImpl implements ConstraintValidator<ContactFormat, Object> {


    private static final String EMAIL_REGEX =
            "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    private static final String TURKISH_PHONE_REGEX =
            "^(?:\\+90|0)?\\s*?(?:\\(?([2-5][0-9]{2})\\)?\\s*?([0-9]{3})\\s*?([0-9]{2})\\s*?([0-9]{2})|([2-5][0-9]{9}))$";



    private String typeFieldName;
    private String valueFieldName;


    @Override
    public void initialize(ContactFormat constraintAnnotation) {
        this.typeFieldName = constraintAnnotation.typeField();
        this.valueFieldName = constraintAnnotation.valueField();
    }


    @Override
    public boolean isValid(Object dtoObject, ConstraintValidatorContext context) {

        BeanWrapper beanWrapper = new BeanWrapperImpl(dtoObject);


        Object typeValue = beanWrapper.getPropertyValue(typeFieldName);
        Object valueValue = beanWrapper.getPropertyValue(valueFieldName);


        if (typeValue == null || valueValue == null || valueValue.toString().trim().isEmpty()) {
            return true;
        }


        String typeString = typeValue.toString();
        String valueString = valueValue.toString();



        if (typeString.equalsIgnoreCase("EMAIL")) {

            return valueString.matches(EMAIL_REGEX);

        } else if (typeString.equalsIgnoreCase("PHONE")) {

            return valueString.matches(TURKISH_PHONE_REGEX);

        }else if (typeString.equalsIgnoreCase("HOMEPHONE")) {

            return true;

        }else if (typeString.equalsIgnoreCase("FAX")) {

            return true;

        }


        return true;
    }
}

//ConstraintValidator<A extends Annotation, T> arayüzü, "A" ile gösterilen bir kısıtlama (constraint)
// anotasyonunu, "T" ile gösterilen belirli bir veri tipine nasıl uygulayacağını tanımlayan sözleşmedir.

//ConstraintValidator arayüzünü uyguladığınızda, mecburen iki metodu implemente etmeniz gerekir:
//void initialize(A constraintAnnotation)
// Doğrulama mekanizması ilk kez oluşturulduğunda (veya kullanıma hazırlandığında) çağrılır.
// Konfigürasyon (Hazırlık): Anotasyonda (sizin örneğinizde @ContactFormat) belirtilen tüm özel parametreleri
// (Örn: typeField(), valueField(), enumClass()) okur ve kural uygulayıcı (validator) sınıfın içine yükler.

//boolean isValid(T value, ConstraintValidatorContext context)
// Gerçek doğrulama işleminin yapıldığı yerdir.
// Bir istemci (client) bir istek gönderdiğinde, doğrulama çalıştığında bu metot çağrılır.
// Kural Uygulama (Doğrulama): Kontrol edilecek nesneyi (value) alır ve iş kurallarınızı uygular.
// Eğer nesne kurallara uyuyorsa true, uymuyorsa false döndürür.