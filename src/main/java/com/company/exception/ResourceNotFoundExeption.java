package com.company.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundExeption extends RuntimeException {
    public ResourceNotFoundExeption() {
        super();
    }

    public ResourceNotFoundExeption(String message) {
        super(message);
    }

    public ResourceNotFoundExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundExeption(Throwable cause) {
        super(cause);
    }
}
