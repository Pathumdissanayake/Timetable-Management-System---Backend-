package com.example.TMS.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Custom exception for resource not found
@ResponseStatus(HttpStatus.NOT_FOUND) // Sets HTTP 404 status when this exception is thrown
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message); // Passes the message to the RuntimeException constructor
    }
}
