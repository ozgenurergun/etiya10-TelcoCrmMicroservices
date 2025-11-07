package com.etiya.common.events.customer;

import java.time.LocalDateTime;

//record Java’da immutable (değiştirilemez) veri taşıyıcıları (data carrier) tanımlamak için kullanılan özel bir sınıf türüdür.
//equals(), hashCode(), toString() gibi metotlar otomatik gelir.
public record CreateCustomerEvent(String customerId,
                                  String customerNumber,
                                  String firstName,
                                  String middleName,
                                  String lastName,
                                  String nationalId,
                                  String dateOfBirth,
                                  String motherName,
                                  String fatherName,
                                  String gender) {
}

//CustomerService tarafında yeni bir müşteri oluşturuldu diyelim.
//Bu durumda sistem, “bir müşteri oluşturuldu” anlamına gelen bir olay (CreateCustomerEvent) yayınlar (publish).

//CustomerService içinde event üretmekte, SearchService veya diğer servislerde event dinlemekte.