package com.poseal.university.exception;

public class HibernateConnectionException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public HibernateConnectionException() {
        super();
    }

    public HibernateConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public HibernateConnectionException(String message) {
        super(message);
    }
}
