package com.lastcivilization.equipmentwriteservice.domain;

import com.lastcivilization.equipmentwriteservice.domain.exception.BackpackIsFullException;
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
import com.lastcivilization.equipmentwriteservice.infrastructure.backpack.BackpackConfig;

import java.util.List;

public class EquipmentService{

    private final EquipmentRepository equipmentRepository;
    private final UserService userService;
    private final ItemService itemService;
    private final BackpackConfig backpackConfig;

    EquipmentService(EquipmentRepository equipmentRepository, UserService userService, ItemService itemService, BackpackConfig backpackConfig) {
        this.equipmentRepository = equipmentRepository;
        this.userService = userService;
        this.itemService = itemService;
        this.backpackConfig = backpackConfig;
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

    private boolean isNotHelmet(ItemDto itemDto) {
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

    public EquipmentModel setArmor(String keycloakId, long id){
        Equipment equipment = getEquipmentByKeycloakId(keycloakId);
        ItemDto itemDto = itemService.getItem(id);
        if(isNotArmor(itemDto)){
            throw new ItemTypeNotMatchException(ItemType.ARMOR);
        }
        removeItemFromBackpack(equipment, id);
        equipment.setArmor(id);
        EquipmentModel equipmentModel = Mapper.toModel(equipment);
        return equipmentRepository.save(equipmentModel);
    }

    private boolean isNotArmor(ItemDto itemDto) {
        return !itemDto.type().equals(ItemType.ARMOR);
    }

    public EquipmentModel setShoes(String keycloakId, long id){
        Equipment equipment = getEquipmentByKeycloakId(keycloakId);
        ItemDto itemDto = itemService.getItem(id);
        if(isNotShoes(itemDto)){
            throw new ItemTypeNotMatchException(ItemType.SHOES);
        }
        removeItemFromBackpack(equipment, id);
        equipment.setShoes(id);
        EquipmentModel equipmentModel = Mapper.toModel(equipment);
        return equipmentRepository.save(equipmentModel);
    }

    private boolean isNotShoes(ItemDto itemDto) {
        return !itemDto.type().equals(ItemType.SHOES);
    }

    public EquipmentModel setPants(String keycloakId, long id){
        Equipment equipment = getEquipmentByKeycloakId(keycloakId);
        ItemDto itemDto = itemService.getItem(id);
        if(isNotPants(itemDto)){
            throw new ItemTypeNotMatchException(ItemType.PANTS);
        }
        removeItemFromBackpack(equipment, id);
        equipment.setPants(id);
        EquipmentModel equipmentModel = Mapper.toModel(equipment);
        return equipmentRepository.save(equipmentModel);
    }

    private boolean isNotPants(ItemDto itemDto) {
        return !itemDto.type().equals(ItemType.PANTS);
    }

    public EquipmentModel setWeapon(String keycloakId, long id){
        Equipment equipment = getEquipmentByKeycloakId(keycloakId);
        ItemDto itemDto = itemService.getItem(id);
        if(isNotWeapon(itemDto)){
            throw new ItemTypeNotMatchException(ItemType.WEAPON);
        }
        removeItemFromBackpack(equipment, id);
        equipment.setWeapon(id);
        EquipmentModel equipmentModel = Mapper.toModel(equipment);
        return equipmentRepository.save(equipmentModel);
    }

    private boolean isNotWeapon(ItemDto itemDto) {
        return !itemDto.type().equals(ItemType.WEAPON);
    }

    public EquipmentModel setShield(String keycloakId, long id){
        Equipment equipment = getEquipmentByKeycloakId(keycloakId);
        ItemDto itemDto = itemService.getItem(id);
        if(isNotShield(itemDto)){
            throw new ItemTypeNotMatchException(ItemType.SHIELD);
        }
        removeItemFromBackpack(equipment, id);
        equipment.setHelmet(id);
        EquipmentModel equipmentModel = Mapper.toModel(equipment);
        return equipmentRepository.save(equipmentModel);
    }

    private boolean isNotShield(ItemDto itemDto) {
        return !itemDto.type().equals(ItemType.SHIELD);
    }

    public EquipmentModel addItemToBackpack(String keycloakId, long id){
        Equipment equipment = getEquipmentByKeycloakId(keycloakId);
        List<BackpackItem> backpack = equipment.getBackpack();
        if(isBackpackFull(backpack)){
            throw new BackpackIsFullException();
        }
        backpack.add(buildBackpackItem(id));
        EquipmentModel equipmentModel = Mapper.toModel(equipment);
        return equipmentRepository.save(equipmentModel);
    }

    private BackpackItem buildBackpackItem(long id) {
        return BackpackItem.Builder.aBackpackItem()
                .itemId(id)
                .build();
    }

    private boolean isBackpackFull(List<BackpackItem> backpack) {
        return backpack.size() == backpackConfig.getSize();
    }
}
