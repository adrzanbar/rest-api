package com.uncode.stop.rest_api.error;

public class UniqueConstraintViolationException extends ServiceException {

    public UniqueConstraintViolationException(String message) {
        super(message);
    }

    public UniqueConstraintViolationException(String message, Throwable cause) {
        super(message, cause);
    }

}
