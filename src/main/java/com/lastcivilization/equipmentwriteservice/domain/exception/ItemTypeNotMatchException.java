package com.lastcivilization.equipmentwriteservice.domain.exception;

import com.lastcivilization.equipmentwriteservice.domain.port.dto.ItemType;

public class ItemTypeNotMatchException extends RuntimeException {

    public ItemTypeNotMatchException(ItemType helmet) {
        super("Item type not match, current type is %s".formatted(helmet.toString()));
    }
}
