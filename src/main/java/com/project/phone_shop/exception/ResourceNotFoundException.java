package com.project.phone_shop.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ApiException {

    //Constructor
    public ResourceNotFoundException(String resourceName, Long id) {
        super(HttpStatus.NOT_FOUND, String.format("%s with id = %d not found", resourceName, id));
    }
}
