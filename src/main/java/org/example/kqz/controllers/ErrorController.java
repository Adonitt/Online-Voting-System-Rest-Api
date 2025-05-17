package org.example.kqz.controllers;

import org.example.kqz.dtos.ErrorResponse;
import org.example.kqz.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ErrorController {
    @ExceptionHandler(Exception.class) // 500 - Internal Server Error
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        var errorResponse = new ErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class) // 401 - Unauthorized
    public ResponseEntity<ErrorResponse> handleException(BadCredentialsException e) {
        var errorResponse = new ErrorResponse("Invalid email or password", HttpStatus.UNAUTHORIZED.value(), null);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler(NotKosovoCitizenException.class)
    public ResponseEntity<ErrorResponse> handleException(NotKosovoCitizenException e) {
        var errorResponse = new ErrorResponse("Incorrect data! Please recheck personal number, first name, last name and birth date!", HttpStatus.BAD_REQUEST.value(), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(MustBe18ToVote.class)
    public ResponseEntity<ErrorResponse> handleException(MustBe18ToVote e) {
        var errorResponse = new ErrorResponse("User must be more than 18 years old to register!", HttpStatus.BAD_REQUEST.value(), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }


    @ExceptionHandler(EmailAlreadyExistsException.class) // 409 - Conflict (email conflict)
    public ResponseEntity<ErrorResponse> handleException(EmailAlreadyExistsException e) {
        var errorResponse = new ErrorResponse("Email already exists!", HttpStatus.CONFLICT.value(), null);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(PersonalNumberAlreadyExists.class) // 409 - Conflict (personal number conflict)
    public ResponseEntity<ErrorResponse> handleException(PersonalNumberAlreadyExists e) {
        var errorResponse = new ErrorResponse("Personal number already exists!", HttpStatus.CONFLICT.value(), null);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(PersonalNumberMustBe10Chars.class)
    public ResponseEntity<ErrorResponse> handleException(PersonalNumberMustBe10Chars e) {
        var errorResponse = new ErrorResponse("Personal number must be 10 characters long!", HttpStatus.BAD_REQUEST.value(), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(PartyNotExistsException.class)
    public ResponseEntity<ErrorResponse> handleException(PartyNotExistsException e) {
        var errorResponse = new ErrorResponse("Party Already Exists with this name or number!", HttpStatus.CONFLICT.value(), null);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(MustChooseBetween1And10Candidates.class)
    public ResponseEntity<ErrorResponse> handleException(MustChooseBetween1And10Candidates e) {
        var errorResponse = new ErrorResponse("You must choose between 1 and 10 candidates!", HttpStatus.BAD_REQUEST.value(), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(AlreadyVotedException.class)
    public ResponseEntity<ErrorResponse> handleException(AlreadyVotedException e) {
        var errorResponse = new ErrorResponse("You have already voted!", HttpStatus.BAD_REQUEST.value(), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(CandidateNumberAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleException(CandidateNumberAlreadyExistsException e) {
        var errorResponse = new ErrorResponse("Candidate number already exists!", HttpStatus.CONFLICT.value(), null);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }


}

