package com.lastcivilization.equipmentwriteservice.infrastructure.application.rest.dto;

import java.util.List;

public record EquipmentDto(
        Long id,
        Long helmet,
        Long armor,
        Long shoes,
        Long pants,
        Long weapon,
        Long shield,
        List<BackpackItemDto> backpack
) { }
