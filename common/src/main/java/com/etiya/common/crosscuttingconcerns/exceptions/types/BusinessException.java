package com.etiya.common.crosscuttingconcerns.exceptions.types;

public class BusinessException extends RuntimeException{
    public BusinessException(String message){
        super(message);
    }
}

//Uygulamanın iş mantığı (domain logic) kurallarının ihlal edildiği durumları temsil eder
// (Örn: "Aynı TC kimlik numarasıyla ikinci bir kayıt yapılamaz", "Hesapta yeterli bakiye yok").
//Kullanım Amacı	Bu istisna fırlatıldığında, hata işleyici (Exception Handler) bunu yakalar ve ona
// karşılık gelen BusinessProblemDetails yanıtını (HTTP 400 Bad Request) döndürür.