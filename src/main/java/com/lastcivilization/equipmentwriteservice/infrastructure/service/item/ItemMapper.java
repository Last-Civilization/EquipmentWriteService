package com.lastcivilization.equipmentwriteservice.infrastructure.service.item;

import com.lastcivilization.equipmentwriteservice.domain.port.dto.ItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
interface ItemMapper {

    ItemMapper MAPPER = Mappers.getMapper(ItemMapper.class);

    ItemDto toDto(Item item);

}
