package com.lastcivilization.equipmentwriteservice.domain.port;

import com.lastcivilization.equipmentwriteservice.domain.port.dto.ItemDto;

public interface ItemService {

    ItemDto getItem(long id);
}
