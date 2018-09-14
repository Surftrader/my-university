package com.poseal.university.exception;

public class DatePeriodException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DatePeriodException() {
        super();
    }

    public DatePeriodException(String message, Throwable cause) {
        super(message, cause);
    }

    public DatePeriodException(String message) {
        super(message);
    }
}
