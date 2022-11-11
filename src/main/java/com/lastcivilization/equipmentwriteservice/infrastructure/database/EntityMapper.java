package com.lastcivilization.equipmentwriteservice.infrastructure.database;

import com.lastcivilization.equipmentwriteservice.domain.view.EquipmentModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
interface EntityMapper {

    EntityMapper MAPPER = Mappers.getMapper(EntityMapper.class);

    EquipmentModel toModel(EquipmentEntity equipmentEntity);
    EquipmentEntity toEntity(EquipmentModel equipmentModel);
}
