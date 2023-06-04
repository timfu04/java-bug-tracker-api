package com.clementlee.bugtrackerapi.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice // handle exceptions from any controller
public class GlobalExceptionHandler {

    // Handles role not found exception
    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleRoleNotFoundException(RoleNotFoundException ex) {
        ExceptionResponse exceptionResponse = createExceptionResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    // Handles validation exceptions
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidationException(MethodArgumentNotValidException ex) {

        // Field errors
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors(); // get all field errors
        List<String> strFieldErrors = fieldErrors.stream()
                .filter(fieldError -> !fieldError.getDefaultMessage().isBlank()) // filter out field errors with empty message
                .map(fieldError -> fieldError.getDefaultMessage()) // get message from each field error
                .collect(Collectors.toList());

        // Non-field errors
        List<ObjectError> nonFieldErrors = ex.getBindingResult().getAllErrors(); // get non-field errors
        List<String> strnNonFieldErrors = nonFieldErrors.stream()
                .filter(nonFieldError -> !nonFieldError.getDefaultMessage().isBlank()) // filter out non-field errors with empty message
                .map(nonFieldError -> nonFieldError.getDefaultMessage()) // get message from each non-field error
                .collect(Collectors.toList());

        List<String> finalErrors = new ArrayList<>();
        if (!strFieldErrors.isEmpty()){ // if field errors string list is not empty
            finalErrors = strFieldErrors;
        } else if (!strnNonFieldErrors.isEmpty()) { // if non-field errors string list is not empty
            finalErrors = strnNonFieldErrors;
        }

        String error_message = String.join(", ", finalErrors); // join list of errors with comma as separator
        ExceptionResponse exceptionResponse = createExceptionResponse(HttpStatus.BAD_REQUEST.value(), error_message, LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    // Handles data integrity exceptions (database related)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionResponse> handleDataIntegrityException(DataIntegrityViolationException ex) {
        ExceptionResponse exceptionResponse =
                createExceptionResponse(HttpStatus.BAD_REQUEST.value(), ex.getMostSpecificCause().getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    // Handles user not found exception
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleUserNotFoundException(UserNotFoundException ex) {
        ExceptionResponse exceptionResponse = createExceptionResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    // Handles project not found exception
    @ExceptionHandler(ProjectNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleProjectNotFoundException(ProjectNotFoundException ex) {
        ExceptionResponse exceptionResponse = createExceptionResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    // Handles status not found exception
    @ExceptionHandler(StatusNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleStatusNotFoundException(StatusNotFoundException ex) {
        ExceptionResponse exceptionResponse = createExceptionResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    // Handles severity not found exception
    @ExceptionHandler(SeverityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleSeverityNotFoundException(SeverityNotFoundException ex) {
        ExceptionResponse exceptionResponse = createExceptionResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    // Handles priority not found exception
    @ExceptionHandler(PriorityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handlePriorityNotFoundException(PriorityNotFoundException ex) {
        ExceptionResponse exceptionResponse = createExceptionResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    // Create exception response
    private ExceptionResponse createExceptionResponse(int statusCode, String message, LocalDateTime timestamp){
        ExceptionResponse response = new ExceptionResponse();
        response.setStatusCode(statusCode);
        response.setMessage(message);
        response.setTimestamp(timestamp);
        return response;
    }

}
