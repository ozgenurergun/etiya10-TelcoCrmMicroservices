package com.etiya.common.validators.annotations;

import com.etiya.common.validators.concretes.EnumValidatorImpl;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented

//Bu kuralın kontrol mantığının EnumValidatorImpl.class içinde bulunduğunu belirtir.
@Constraint(validatedBy = EnumValidatorImpl.class)

//Bu anotasyonun bir metot veya bir alan (field) üzerine konulabileceğini belirtir.
// (@ContactFormat bir sınıf üzerine konuyordu).
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)


public @interface EnumValidator {

    //Bu, @EnumValidator anotasyonunu kullanırken zorunlu olarak belirtmeniz gereken özel parametredir.
    // Kullanıcı, hangi Enum sınıfına karşı doğrulama yapılacağını buraya yazar.
    Class<? extends Enum<?>> enumClass();


    String message() default "Değer, izin verilenler listesinde değil.";


    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

//Amaç:Bir alanın değerinin, belirli bir Enum (Sabitler Kümesi) içindeki değerlerden biri olup olmadığını kontrol etmek.