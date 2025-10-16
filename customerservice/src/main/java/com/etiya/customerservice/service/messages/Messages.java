package com.etiya.customerservice.service.messages;

//Messages sınıfı tüm mesaj anahtarlarını merkezi olarak tutar ve uygulamanın her yerinde aynı anahtarı kullanarak
//localization ve hata mesajlarını yönetmeyi kolaylaştırır.
//Kısaca: Sabit mesaj anahtarlarının merkezi deposu.
public class Messages {

    public static final String NationalityIdentityExists = "nationalityIdentityExists"; //bu bir message key
    public static final String AddressExists = "addressExists";
    public static final String TaxNumberExists = "taxNumberExists";
    public static final String CheckIsPrimary =  "checkIsPrimary";
    public static final String CityExists = "cityExists";
    public static final String IdExists = "idExists";
    public static final String BillingAccountActive = "billingAccountActive";
    public static final String ChangeBillingAccountType = "changeBillingAccountType";
    public static final String AddressBelongsToCustomer =  "addressBelongsToCustomer";
    public static final String StatusTransitionIsValid = "statusTransitionIsValid";
    public static final String CustomerTypeMatchesAccountType = "customerTypeMatchesAccountType";
    public static final String CustomerHasAddress =  "customerHasAddress";
    public static final String BillingAccountExists =  "billingAccountExists";

}
