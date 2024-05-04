package com.example.TMS.Exception.UserExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidDOBException extends RuntimeException {
    public InvalidDOBException(String message) {
        super(message);
    }
}
