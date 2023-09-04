package com.chatapp.controlleradvice;

import com.chatapp.exception.DuplicateUsernameException;
import com.chatapp.model.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> runtimeExceptionHandling(RuntimeException e) {
        ErrorModel messageModel = new ErrorModel(e.getMessage());
        return new ResponseEntity<>(messageModel, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateUsernameException.class)
    public ResponseEntity<?> duplicateUsernameExceptionHandling(DuplicateUsernameException e) {
        ErrorModel messageModel = new ErrorModel(e.getMessage());
        return new ResponseEntity<>(messageModel, HttpStatus.BAD_REQUEST);
    }
}
