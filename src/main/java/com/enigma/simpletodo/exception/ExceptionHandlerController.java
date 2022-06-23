package com.enigma.simpletodo.exception;

import com.enigma.simpletodo.utils.WebResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(NotFoundException e) {
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        WebResponse<String> webResponse = new WebResponse<>(
                404,
                notFound,
                e.getMessage(),
                null
                );
        return new ResponseEntity<>(webResponse, notFound);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        WebResponse<String> webResponse = new WebResponse<>(
                400,
                badRequest,
                e.getMessage(),
                null
                );
        return new ResponseEntity<>(webResponse, badRequest);
    }

    @ExceptionHandler(value = {NotAcceptableException.class})
    public ResponseEntity<Object> handleNotAcceptableException(NotAcceptableException e) {
        HttpStatus notAcceptable = HttpStatus.NOT_ACCEPTABLE;
        WebResponse<String> webResponse = new WebResponse<>(
                406,
                notAcceptable,
                e.getMessage(),
                null
                );
        return new ResponseEntity<>(webResponse, notAcceptable);
    }

    @ExceptionHandler(value = {DataExistException.class, })
    public ResponseEntity<Object> handleDataExistException(DataExistException e) {
        HttpStatus conflict = HttpStatus.CONFLICT;
        WebResponse<String> webResponse = new WebResponse<>(
                409,
                conflict,
                e.getMessage(),
                null
        );
        return new ResponseEntity<>(webResponse, conflict);
    }


    @ExceptionHandler(value = {UnauthorizedException.class, AuthenticationException.class})
    public ResponseEntity<Object> handleUnauthorizedException(UnauthorizedException e) {
        HttpStatus unauthorized = HttpStatus.UNAUTHORIZED;
        WebResponse<String> webResponse = new WebResponse<>(
                401,
                unauthorized,
                e.getMessage(),
                null
        );
        return new ResponseEntity<>(webResponse, unauthorized);
    }

}
