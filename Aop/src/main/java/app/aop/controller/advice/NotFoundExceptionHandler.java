package app.aop.controller.advice;

import app.aop.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class NotFoundExceptionHandler {

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Response> handleException(NotFoundException e) {
        Response response = new Response(HttpStatus.NOT_FOUND.value(), e.getMessage(), Instant.now());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}