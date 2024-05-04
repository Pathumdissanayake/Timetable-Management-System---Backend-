package com.example.TMS.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidStudentIdException extends RuntimeException {
    public InvalidStudentIdException(String message) {
        super(message);
    }
}
