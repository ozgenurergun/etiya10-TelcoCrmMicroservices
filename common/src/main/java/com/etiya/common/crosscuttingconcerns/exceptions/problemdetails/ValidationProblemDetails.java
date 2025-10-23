package com.etiya.common.crosscuttingconcerns.exceptions.problemdetails;

import com.etiya.common.crosscuttingconcerns.exceptions.constants.ExceptionMessage;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class ValidationProblemDetails extends ProblemDetails{
    private Map<String, String> validationErrors;

    public ValidationProblemDetails() {
        setTitle(ExceptionMessage.VALIDATION_ERROR);
        setType(ExceptionMessage.TYPE_VALIDATION);
        setStatus(HttpStatus.BAD_REQUEST.value());
    }

    public Map<String, String> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(Map<String, String> validationErrors) {
        this.validationErrors = validationErrors;
    }
}

//Kullanım Amacı: Giriş verilerinin (form/JSON gövdesi) doğrulama kurallarına uymadığı durumlarda (Örn: @NotNull ihlali).

//Best Practice: Ek Bilgi (Validation Errors): Bu sınıf, sadece genel hata mesajı yerine, validationErrors adında ek bir
// alan içerir. Bu alan, hangi alanın (field) hangi hatayı (error message) içerdiğini gösteren bir Map (anahtar/değer
// çifti) taşır. Bu, istemcinin hangi input alanını düzeltmesi gerektiğini net bir şekilde göstererek kullanıcı
// deneyimini maksimize eder. Bu daima bir API best practice'idir.