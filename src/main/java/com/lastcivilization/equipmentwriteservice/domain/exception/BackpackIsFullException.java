package com.lastcivilization.equipmentwriteservice.domain.exception;

public class BackpackIsFullException extends RuntimeException {

    public BackpackIsFullException() {
        super("Backpack is full!");
    }
}
