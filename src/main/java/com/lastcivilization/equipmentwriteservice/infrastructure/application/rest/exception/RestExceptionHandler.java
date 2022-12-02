package com.lastcivilization.equipmentwriteservice.infrastructure.application.rest.exception;

import com.lastcivilization.equipmentwriteservice.domain.exception.ApplicationException;
import com.lastcivilization.equipmentwriteservice.domain.exception.BackpackIsFullException;
import com.lastcivilization.equipmentwriteservice.domain.exception.EquipmentNotFoundException;
import com.lastcivilization.equipmentwriteservice.domain.exception.ItemNotFoundException;
import com.lastcivilization.equipmentwriteservice.domain.exception.ItemNotInBackpackException;
import com.lastcivilization.equipmentwriteservice.domain.exception.ItemTypeNotMatchException;
import com.lastcivilization.equipmentwriteservice.domain.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
class RestExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    ErrorEntity handleApplicationException(ApplicationException exception){
        return new ErrorEntity(exception.getMessage());
    }

    @ExceptionHandler(BackpackIsFullException.class)
    @ResponseStatus(BAD_REQUEST)
    ErrorEntity handleBackpackIsFullException(BackpackIsFullException exception){
        return new ErrorEntity(exception.getMessage());
    }

    @ExceptionHandler(EquipmentNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    ErrorEntity handleEquipmentNotFoundException(EquipmentNotFoundException exception){
        return new ErrorEntity(exception.getMessage());
    }

    @ExceptionHandler(ItemNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    ErrorEntity handleItemNotFoundException(ItemNotFoundException exception){
        return new ErrorEntity(exception.getMessage());
    }

    @ExceptionHandler(ItemNotInBackpackException.class)
    @ResponseStatus(BAD_REQUEST)
    ErrorEntity handleItemNotInBackpackException(ItemNotInBackpackException exception){
        return new ErrorEntity(exception.getMessage());
    }

    @ExceptionHandler(ItemTypeNotMatchException.class)
    @ResponseStatus(BAD_REQUEST)
    ErrorEntity handleItemTypeNotMatchException(ItemTypeNotMatchException exception){
        return new ErrorEntity(exception.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    ErrorEntity handleUserNotFoundException(UserNotFoundException exception){
        return new ErrorEntity(exception.getMessage());
    }
}
