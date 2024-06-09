package com.example.servidorseguridadinesmr.ui.exceptions;

import com.example.servidorseguridadinesmr.domain.model.error.ErrorSec;
import com.example.servidorseguridadinesmr.domain.model.error.exceptions.DatabaseException;
import com.example.servidorseguridadinesmr.domain.model.error.exceptions.EmailException;
import com.example.servidorseguridadinesmr.domain.model.error.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ValidationException.class)
    @ResponseBody
    public ResponseEntity<ErrorSec> handleValidationException(ValidationException e) {
        ErrorSec apiError = new ErrorSec(0, e.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(DatabaseException.class)
    @ResponseBody
    public ResponseEntity<ErrorSec> handleDatabaseException(DatabaseException e) {
        ErrorSec apiError = new ErrorSec(0, e.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(EmailException.class)
    @ResponseBody
    public ResponseEntity<ErrorSec> handleDatabaseException(EmailException e) {
        ErrorSec apiError = new ErrorSec(0, e.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(apiError);
    }
}
