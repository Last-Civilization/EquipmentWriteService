package com.lastcivilization.equipmentwriteservice.domain.view;

import java.util.List;

public record EquipmentModel(
        Long id,
        Long helmet,
        Long armor,
        Long boots,
        Long pants,
        Long weapon,
        Long shield,
        List<BackpackItemModel> backpack
) {

}
