package com.unisonwave.rgt.control.common;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.unisonwave.rgt.control.exception.DuplicateCampaignException;

import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateCampaignException.class)
    public ResponseEntity<Map<String, String>> handleDuplicateCampaign(DuplicateCampaignException ex) {
        Map<String, String> body = new HashMap<>();
        body.put("error", "DUPLICATE_CAMPAIGN");
        body.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgument(IllegalArgumentException ex) {
        Map<String, String> body = new HashMap<>();
        body.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}


