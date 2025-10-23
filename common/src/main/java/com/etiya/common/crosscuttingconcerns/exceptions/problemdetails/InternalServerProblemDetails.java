package com.etiya.common.crosscuttingconcerns.exceptions.problemdetails;

import com.etiya.common.crosscuttingconcerns.exceptions.constants.ExceptionMessage;
import org.springframework.http.HttpStatus;

public class InternalServerProblemDetails extends ProblemDetails {
    public InternalServerProblemDetails() {
        setTitle(ExceptionMessage.INTERNAL_ERROR);
        setType(ExceptionMessage.TYPE_INTERNAL);
        setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}

//Kullanım Amacı: Beklenmeyen hatalar (veritabanı bağlantı kopması, null pointer vb.).