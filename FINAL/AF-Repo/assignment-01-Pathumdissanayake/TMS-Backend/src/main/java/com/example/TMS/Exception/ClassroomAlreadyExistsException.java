package com.example.TMS.Exception;

public class ClassroomAlreadyExistsException extends RuntimeException {
    // Constructor that accepts a custom message
    public ClassroomAlreadyExistsException(String message) {
        super(message); // Pass the message to the RuntimeException constructor
    }
}
