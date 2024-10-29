package com.example.cache.customcache.exception;

public class EmployeeDeleteException extends Exception {
    public EmployeeDeleteException () {

    }

    public EmployeeDeleteException (String message) {
        super (message);
    }

    public EmployeeDeleteException (Throwable cause) {
        super (cause);
    }

    public EmployeeDeleteException (String message, Throwable cause) {
        super (message, cause);
    }
}