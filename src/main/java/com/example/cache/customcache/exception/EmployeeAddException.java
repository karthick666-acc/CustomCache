package com.example.cache.customcache.exception;

public class EmployeeAddException extends Exception {
    public EmployeeAddException () {

    }

    public EmployeeAddException (String message) {
        super (message);
    }

    public EmployeeAddException (Throwable cause) {
        super (cause);
    }

    public EmployeeAddException (String message, Throwable cause) {
        super (message, cause);
    }
}
