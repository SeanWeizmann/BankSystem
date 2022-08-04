package com.sean.bankSystem.advice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


public class RestException {


    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDetail handleException(Exception e) {
        return new ErrorDetail("System Error", e.getMessage());
    }
}
