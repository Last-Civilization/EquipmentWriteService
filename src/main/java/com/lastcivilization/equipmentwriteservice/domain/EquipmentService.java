package com.lastcivilization.equipmentwriteservice.domain;

import com.lastcivilization.equipmentwriteservice.domain.exception.EquipmentNotFoundException;
import com.lastcivilization.equipmentwriteservice.domain.exception.ItemNotInBackpackException;
import com.lastcivilization.equipmentwriteservice.domain.exception.ItemTypeNotMatchException;
import com.lastcivilization.equipmentwriteservice.domain.port.EquipmentRepository;
import com.lastcivilization.equipmentwriteservice.domain.port.ItemService;
import com.lastcivilization.equipmentwriteservice.domain.port.UserService;
import com.lastcivilization.equipmentwriteservice.domain.port.dto.ItemDto;
import com.lastcivilization.equipmentwriteservice.domain.port.dto.ItemType;
import com.lastcivilization.equipmentwriteservice.domain.port.dto.UserDto;
import com.lastcivilization.equipmentwriteservice.domain.view.EquipmentModel;

import java.util.List;

public class EquipmentService{

    private final EquipmentRepository equipmentRepository;
    private final UserService userService;
    private final ItemService itemService;

    EquipmentService(EquipmentRepository equipmentRepository, UserService userService, ItemService itemService) {
        this.equipmentRepository = equipmentRepository;
        this.userService = userService;
        this.itemService = itemService;
    }

    public EquipmentModel createEquipment(){
        Equipment equipment = Equipment.Builder.anEquipment().build();
        EquipmentModel equipmentModel = Mapper.toModel(equipment);
        return equipmentRepository.save(equipmentModel);
    }

    public void deleteEquipment(long id){
        equipmentRepository.deleteById(id);
    }

    public EquipmentModel setHelmet(String keycloakId, long id){
        Equipment equipment = getEquipmentByKeycloakId(keycloakId);
        ItemDto itemDto = itemService.getItem(id);
        if(isNotHelmet(itemDto)){
            throw new ItemTypeNotMatchException(ItemType.HELMET);
        }
        removeItemFromBackpack(equipment, id);
        equipment.setHelmet(id);
        EquipmentModel equipmentModel = Mapper.toModel(equipment);
        return equipmentRepository.save(equipmentModel);
    }

    private static boolean isNotHelmet(ItemDto itemDto) {
        return !itemDto.type().equals(ItemType.HELMET);
    }

    private void removeItemFromBackpack(Equipment equipment, long id) {
        List<BackpackItem> backpack = equipment.getBackpack();
        for (BackpackItem backpackItem : backpack){
            if(backpackItem.getItemId() == id){
                backpack.remove(backpackItem);
                return;
            }
        }
        throw new ItemNotInBackpackException(id);
    }

    private Equipment getEquipmentByKeycloakId(String keycloakId){
        UserDto userDto = userService.getUser(keycloakId);
        EquipmentModel equipmentModel = getEquipmentModel(userDto.equipment());
        return Mapper.toDomain(equipmentModel);
    }

    private EquipmentModel getEquipmentModel(long id) {
        return equipmentRepository.findById(id)
                .orElseThrow(() -> new EquipmentNotFoundException(id));
    }
}
