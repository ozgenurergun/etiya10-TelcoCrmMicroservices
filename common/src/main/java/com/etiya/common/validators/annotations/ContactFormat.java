package com.etiya.common.validators.annotations;

import com.etiya.common.validators.concretes.ContactFormatImpl;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented

//Bağlayıcı: Spring'e (daha doğrusu Bean Validation API'ye) bu kuralı (yani @ContactFormat'ı) kimin kontrol
// edeceğini söyler. Kontrol eden mantık, ContactFormatImpl.class sınıfında yazılıdır.
@Constraint(validatedBy = ContactFormatImpl.class)

//Bu anotasyonun nerede kullanılabileceğini belirler. TYPE demek, bu anotasyonun bir Sınıf veya Arayüz
// üzerine konulabileceği anlamına gelir.
@Target(ElementType.TYPE)

//Bu anotasyonun ne zamana kadar tutulacağını belirler. RUNTIME demek, kod çalışırken (Runtime) bu anotasyonun
// okunabilir olduğu anlamına gelir.
@Retention(RetentionPolicy.RUNTIME)
public @interface ContactFormat { //Bu, sizin özel doğrulama kuralınızın adıdır.
    // Tıpkı @NotNull veya @Size gibi, bu da artık kodunuzda kullanabileceğiniz bir anotasyondur.

    String typeField();
    String valueField();


    String message() default "İletişim tipine göre format hatalıdır.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

//Bu, bir Best Practice'tir çünkü standart @Email veya @Pattern gibi anotasyonlar, karmaşık iş
// kurallarınızı (Örn: "eğer tip 'Telefon' ise, 11 haneli olmalıdır") karşılayamaz.