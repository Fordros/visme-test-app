package com.visme.app.exception;


import org.springframework.http.HttpStatus;

import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ApiErrorResponse {

    int code;
    Date timestamp;
    String message;
    String cause;
    Map<String, String> errors;

    ApiErrorResponse() {
        timestamp = new Date();
        code = 500;
    }

    public ApiErrorResponse(ConstraintViolationException e) {
        code = HttpStatus.UNPROCESSABLE_ENTITY.value();
        timestamp = new Date();
        cause = e.toString();
        errors = new HashMap<>();
        e.getConstraintViolations().forEach(i -> errors.put(i.getPropertyPath().toString(), i.getMessage()));
    }
}
