package server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler( {MethodArgumentTypeMismatchException.class})
    public void handleMethodArgumentTypeMismatch() {
        //
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler( {NoSuchElementException.class})
    public void handleNoSuchElementException() {
    }
}
