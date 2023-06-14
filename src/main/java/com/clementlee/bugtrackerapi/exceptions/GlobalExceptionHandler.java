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

@RestControllerAdvice // An interceptor of exceptions thrown by methods annotated with @RequestMapping
public class GlobalExceptionHandler {

    // Handles RoleNotFoundException
    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleRoleNotFoundException(RoleNotFoundException ex) {
        ExceptionResponse exceptionResponse = createExceptionResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    // Handles UserNotFoundException
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleUserNotFoundException(UserNotFoundException ex) {
        ExceptionResponse exceptionResponse = createExceptionResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    // Handles ProjectNotFoundException
    @ExceptionHandler(ProjectNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleProjectNotFoundException(ProjectNotFoundException ex) {
        ExceptionResponse exceptionResponse = createExceptionResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    // Handles ProjectNotCreatedByThisUserException
    @ExceptionHandler(ProjectNotCreatedByThisUserException.class)
    public ResponseEntity<ExceptionResponse> handleProjectNotCreatedByThisUserException(ProjectNotCreatedByThisUserException ex) {
        ExceptionResponse exceptionResponse = createExceptionResponse(HttpStatus.CONFLICT.value(), ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }

    // Handles UserNotInProjectException
    @ExceptionHandler(UserNotInProjectException.class)
    public ResponseEntity<ExceptionResponse> handleUserNotInProjectException(UserNotInProjectException ex) {
        ExceptionResponse exceptionResponse = createExceptionResponse(HttpStatus.CONFLICT.value(), ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }

    // Handles StatusNotFoundException
    @ExceptionHandler(StatusNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleStatusNotFoundException(StatusNotFoundException ex) {
        ExceptionResponse exceptionResponse = createExceptionResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    // Handles SeverityNotFoundException
    @ExceptionHandler(SeverityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleSeverityNotFoundException(SeverityNotFoundException ex) {
        ExceptionResponse exceptionResponse = createExceptionResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    // Handles PriorityNotFoundException
    @ExceptionHandler(PriorityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handlePriorityNotFoundException(PriorityNotFoundException ex) {
        ExceptionResponse exceptionResponse = createExceptionResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    // Handles IssueNotFoundException
    @ExceptionHandler(IssueNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleIssueNotFoundException(IssueNotFoundException ex) {
        ExceptionResponse exceptionResponse = createExceptionResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    // Handles IssueCommentNotFoundException
    @ExceptionHandler(IssueCommentNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleIssueCommentNotFoundException(IssueCommentNotFoundException ex) {
        ExceptionResponse exceptionResponse = createExceptionResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    // Handles DuplicateUserInProjectException
    @ExceptionHandler(DuplicateUserInProjectException.class)
    public ResponseEntity<ExceptionResponse> handleDuplicateUserInProjectException(DuplicateUserInProjectException ex) {
        ExceptionResponse exceptionResponse = createExceptionResponse(HttpStatus.CONFLICT.value(), ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }

    // Handles validation exceptions
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidationException(MethodArgumentNotValidException ex) {

        // Field errors
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors(); // Get a list of all field errors
        List<String> strFieldErrors = fieldErrors.stream()
                .filter(fieldError -> !fieldError.getDefaultMessage().isBlank()) // Remove field errors with empty message
                .map(fieldError -> fieldError.getDefaultMessage()) // Get message from each field error
                .collect(Collectors.toList());

        // Non-field errors
        List<ObjectError> nonFieldErrors = ex.getBindingResult().getAllErrors(); // Get a list of all non-field errors
        List<String> strnNonFieldErrors = nonFieldErrors.stream()
                .filter(nonFieldError -> !nonFieldError.getDefaultMessage().isBlank()) // Remove non-field errors with empty message
                .map(nonFieldError -> nonFieldError.getDefaultMessage()) // Get message from each non-field error
                .collect(Collectors.toList());

        // Choose errors to proceed
        List<String> errorsChosen = new ArrayList<>();
        if (!strFieldErrors.isEmpty()){ // If list of string field errors is not empty
            errorsChosen = strFieldErrors;
        } else if (!strnNonFieldErrors.isEmpty()) { // If list of string non-field errors is not empty
            errorsChosen = strnNonFieldErrors;
        }

        // Format errors chosen if list size more than 1
        List<String> finalErrors = new ArrayList<>();
        if (errorsChosen.size() > 1){
            for (int i = 0; i < errorsChosen.size(); i++) {
                String formatted = String.format("[%d. %s]", i+1, errorsChosen.get(i)); // Add index and square brackets
                finalErrors.add(formatted);
            }
        }else {
            finalErrors = errorsChosen;
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

    // Create exception response
    private ExceptionResponse createExceptionResponse(int statusCode, String message, LocalDateTime timestamp){
        ExceptionResponse response = new ExceptionResponse();
        response.setStatusCode(statusCode);
        response.setMessage(message);
        response.setTimestamp(timestamp);
        return response;
    }

}
