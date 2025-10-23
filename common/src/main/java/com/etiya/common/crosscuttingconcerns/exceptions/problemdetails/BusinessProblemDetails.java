package com.etiya.common.crosscuttingconcerns.exceptions.problemdetails;

import com.etiya.common.crosscuttingconcerns.exceptions.constants.ExceptionMessage;
import org.springframework.http.HttpStatus;

public class BusinessProblemDetails extends ProblemDetails {
    public BusinessProblemDetails() {
        setTitle(ExceptionMessage.BUSINESS_RULE_VIOLATION);
        setType(ExceptionMessage.TYPE_BUSINESS);
        setStatus(HttpStatus.BAD_REQUEST.value());
    }
}

//Kullanım Amacı: İş mantığı kuralları ihlal edildiğinde (Örn: "Bu telefon numarası zaten kayıtlı").