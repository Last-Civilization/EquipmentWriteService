package com.lastcivilization.equipmentwriteservice.domain.exception;

public class ItemNotInBackpackException extends RuntimeException {

    public ItemNotInBackpackException(long id) {
        super("Can not found item with id %d in backpack!".formatted(id));
    }
}
