package com.lastcivilization.equipmentwriteservice.domain.port;

import com.lastcivilization.equipmentwriteservice.domain.port.dto.ItemDto;

import java.util.Optional;

public interface ItemService {

    Optional<ItemDto> getItem(long id);
    boolean isItem(long id);
}
