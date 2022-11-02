package com.lastcivilization.equipmentwriteservice.domain;

import com.lastcivilization.equipmentwriteservice.domain.view.BackpackItemModel;
import com.lastcivilization.equipmentwriteservice.domain.view.EquipmentModel;

import java.util.List;
import java.util.stream.Collectors;

class Mapper {

    private Mapper(){
        throw new RuntimeException("You can not create instance of this class!");
    }

    static EquipmentModel toModel(Equipment equipment){
        return new EquipmentModel(
                equipment.getId(),
                equipment.getHelmet(),
                equipment.getArmor(),
                equipment.getBoots(),
                equipment.getPants(),
                equipment.getWeapon(),
                equipment.getShield(),
                toModel(equipment.getBackpack())
        );
    }

    private static List<BackpackItemModel> toModel(List<BackpackItem> backpack){
        return backpack.stream()
                .map(Mapper::toModel)
                .collect(Collectors.toList());
    }

    private static BackpackItemModel toModel(BackpackItem backpackItem) {
        return new BackpackItemModel(
                backpackItem.getId(),
                backpackItem.getItemId(),
                backpackItem.getAmount()
        );
    }

    public static Equipment toDomain(EquipmentModel equipmentModel) {
        return null;
    }
}
