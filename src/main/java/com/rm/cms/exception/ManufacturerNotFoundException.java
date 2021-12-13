package com.rm.cms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "manufacturer not found ")
public class ManufacturerNotFoundException extends RuntimeException {

    public ManufacturerNotFoundException(String message) {
        super(message);
    }
}
