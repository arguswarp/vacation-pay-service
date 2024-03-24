package com.argus.vacationpayservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ConstraintViolationException.class})
    private ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e) {
        log.error(e.getMessage());
        return ResponseEntity.badRequest().body(e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(" ")));
    }

    @ExceptionHandler({CalendarNotExistException.class})
    private ResponseEntity<Object> handleCalendarNotExistException(CalendarNotExistException e) {
        log.error(e.getCause().getMessage() + Arrays.toString(e.getStackTrace()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler
    private ResponseEntity<Object> handleOtherExceptions(RuntimeException e) {
        log.error(e.getMessage() + Arrays.toString(e.getStackTrace()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
