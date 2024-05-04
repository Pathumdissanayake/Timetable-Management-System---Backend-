package com.example.TMS.Exception;

import com.example.TMS.Exception.UserExceptions.InvalidAcademicYearException;
import com.example.TMS.Exception.UserExceptions.InvalidDOBException;
import com.example.TMS.Exception.UserExceptions.InvalidNICException;
import com.example.TMS.Exception.UserExceptions.InvalidPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// Global exception handler to catch exceptions throughout the application
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class) // Catches ResourceNotFoundException
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        // Return HTTP status 404 with the exception message
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(com.example.TMS.Exception.InvalidEmailException.class)
    public ResponseEntity<String> handleInvalidEmailException(com.example.TMS.Exception.InvalidEmailException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(com.example.TMS.Exception.InvalidStudentIdException.class)
    public ResponseEntity<String> handleInvalidStudentIdException(com.example.TMS.Exception.InvalidStudentIdException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidNICException.class)
    public ResponseEntity<String> handleInvalidNICException(InvalidNICException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<String> handleInvalidPasswordException(InvalidPasswordException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidAcademicYearException.class)
    public ResponseEntity<String> handleInvalidAcademicYearException(InvalidAcademicYearException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidDOBException.class)
    public ResponseEntity<String> handleInvalidDOBException(InvalidDOBException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>("An unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
