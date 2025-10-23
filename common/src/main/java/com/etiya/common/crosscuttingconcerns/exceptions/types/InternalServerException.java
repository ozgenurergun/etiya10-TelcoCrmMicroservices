package com.etiya.common.crosscuttingconcerns.exceptions.types;

public class InternalServerException extends RuntimeException {
    public InternalServerException(String message){
        super(message);
    }
}

//Uygulamanın içeriden kaynaklanan, genellikle beklenmeyen veya çözümlenmesi istemcinin sorumluluğunda
// olmayan hataları temsil eder (Örn: Veritabanına ulaşılamıyor, üçüncü parti bir servisten hatalı yanıt
// alındı, kodda NullPointerException oluştu).
//Kullanım Amacı	Bu istisna fırlatıldığında, hata işleyici bunu yakalar ve ona karşılık gelen
// InternalServerProblemDetails yanıtını (HTTP 500 Internal Server Error) döndürür.