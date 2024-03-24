package com.argus.vacationpayservice.exception;

public class CalendarNotExistException extends RuntimeException{

    public CalendarNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
