package com.lastcivilization.equipmentwriteservice.domain.exception;

public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException(long id) {
        super("Can not found item in game with id %d".formatted(id));
    }
}
