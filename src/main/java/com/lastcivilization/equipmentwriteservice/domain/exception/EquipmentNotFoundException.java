package com.lastcivilization.equipmentwriteservice.domain.exception;

public class EquipmentNotFoundException extends RuntimeException {

    public EquipmentNotFoundException(long id) {
        super("Can not found equipment with id %d".formatted(id));
    }
}
