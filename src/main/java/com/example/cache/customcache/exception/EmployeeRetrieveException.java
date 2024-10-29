package com.example.cache.customcache.exception;

public class EmployeeRetrieveException extends Exception {
    public EmployeeRetrieveException () {

    }

    public EmployeeRetrieveException (String message) {
        super (message);
    }

    public EmployeeRetrieveException (Throwable cause) {
        super (cause);
    }

    public EmployeeRetrieveException (String message, Throwable cause) {
        super (message, cause);
    }
}