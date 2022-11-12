package com.lastcivilization.equipmentwriteservice.infrastructure.application.rest;

import com.lastcivilization.equipmentwriteservice.domain.view.EquipmentModel;
import com.lastcivilization.equipmentwriteservice.infrastructure.application.rest.dto.EquipmentDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
interface RestMapper {

    RestMapper MAPPER = Mappers.getMapper(RestMapper.class);

    EquipmentDto toDto(EquipmentModel equipmentModel);
}
