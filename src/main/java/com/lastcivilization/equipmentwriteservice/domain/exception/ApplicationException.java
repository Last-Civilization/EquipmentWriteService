package com.lastcivilization.equipmentwriteservice.domain.exception;

import feign.FeignException;

public class ApplicationException extends RuntimeException {

    public ApplicationException(Exception exception) {
        super(exception);
    }
}
