package com.lastcivilization.equipmentwriteservice.domain;

import com.lastcivilization.equipmentwriteservice.domain.exception.BackpackIsFullException;
import com.lastcivilization.equipmentwriteservice.domain.exception.EquipmentNotFoundException;
import com.lastcivilization.equipmentwriteservice.domain.exception.ItemNotFoundException;
import com.lastcivilization.equipmentwriteservice.domain.exception.ItemNotInBackpackException;
import com.lastcivilization.equipmentwriteservice.domain.exception.ItemTypeNotMatchException;
import com.lastcivilization.equipmentwriteservice.domain.exception.UserNotFoundException;
import com.lastcivilization.equipmentwriteservice.domain.port.EquipmentRepository;
import com.lastcivilization.equipmentwriteservice.domain.port.ItemService;
import com.lastcivilization.equipmentwriteservice.domain.port.UserService;
import com.lastcivilization.equipmentwriteservice.domain.port.dto.ItemDto;
import com.lastcivilization.equipmentwriteservice.domain.port.dto.ItemType;
import com.lastcivilization.equipmentwriteservice.domain.port.dto.UserDto;
import com.lastcivilization.equipmentwriteservice.domain.view.EquipmentModel;
import com.lastcivilization.equipmentwriteservice.infrastructure.backpack.BackpackConfig;

import java.util.List;
import java.util.Optional;

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

    public EquipmentModel setHelmet(String keycloakId, Long id){
        Equipment equipment = getEquipmentByKeycloakId(keycloakId);
        ItemDto itemDto = getItem(id);
        if(isNotHelmet(itemDto)){
            throw new ItemTypeNotMatchException(ItemType.HELMET);
        }
        removeItemFromBackpack(equipment, id);
        if(haveHelmetBefore(equipment)){
            addItemToBackpack(equipment.getHelmet(), equipment.getBackpack());
        }
        equipment.setHelmet(id);
        EquipmentModel equipmentModel = Mapper.toModel(equipment);
        return equipmentRepository.save(equipmentModel);
    }

    private boolean haveHelmetBefore(Equipment equipment) {
        return equipment.getHelmet() != null;
    }

    private boolean isNotHelmet(ItemDto itemDto) {
        return !itemDto.type().equals(ItemType.HELMET);
    }

    private void removeItemFromBackpack(Equipment equipment, long itemId) {
        List<BackpackItem> backpack = equipment.getBackpack();
        for (BackpackItem backpackItem : backpack){
            if(backpackItem.getItemId() == itemId){
                backpack.remove(backpackItem);
                return;
            }
        }
        throw new ItemNotInBackpackException(itemId);
    }

    private Equipment getEquipmentByKeycloakId(String keycloakId){
        UserDto userDto = userService.getUser(keycloakId)
                .orElseThrow(() -> new UserNotFoundException(keycloakId));
        EquipmentModel equipmentModel = getEquipmentModel(userDto.equipment());
        return Mapper.toDomain(equipmentModel);
    }

    private EquipmentModel getEquipmentModel(long id) {
        return equipmentRepository.findById(id)
                .orElseThrow(() -> new EquipmentNotFoundException(id));
    }

    public EquipmentModel setArmor(String keycloakId, Long id){
        Equipment equipment = getEquipmentByKeycloakId(keycloakId);
        ItemDto itemDto = getItem(id);
        if(isNotArmor(itemDto)){
            throw new ItemTypeNotMatchException(ItemType.ARMOR);
        }
        removeItemFromBackpack(equipment, id);
        if(haveArmorBefore(equipment)){
            addItemToBackpack(equipment.getArmor(), equipment.getBackpack());
        }
        equipment.setArmor(id);
        EquipmentModel equipmentModel = Mapper.toModel(equipment);
        return equipmentRepository.save(equipmentModel);
    }

    private ItemDto getItem(Long id) {
        return itemService.getItem(id)
                .orElseThrow(() -> new ItemNotFoundException(id));
    }

    private boolean haveArmorBefore(Equipment equipment) {
        return equipment.getArmor() != null;
    }

    private boolean isNotArmor(ItemDto itemDto) {
        return !itemDto.type().equals(ItemType.ARMOR);
    }

    public EquipmentModel setShoes(String keycloakId, Long id){
        Equipment equipment = getEquipmentByKeycloakId(keycloakId);
        ItemDto itemDto = getItem(id);
        if(isNotShoes(itemDto)){
            throw new ItemTypeNotMatchException(ItemType.SHOES);
        }
        removeItemFromBackpack(equipment, id);
        if(haveShoesBefore(equipment)){
            addItemToBackpack(equipment.getShoes(), equipment.getBackpack());
        }
        equipment.setShoes(id);
        EquipmentModel equipmentModel = Mapper.toModel(equipment);
        return equipmentRepository.save(equipmentModel);
    }

    private boolean haveShoesBefore(Equipment equipment) {
        return equipment.getShoes() != null;
    }

    private boolean isNotShoes(ItemDto itemDto) {
        return !itemDto.type().equals(ItemType.SHOES);
    }

    public EquipmentModel setPants(String keycloakId, Long id){
        Equipment equipment = getEquipmentByKeycloakId(keycloakId);
        ItemDto itemDto = getItem(id);
        if(isNotPants(itemDto)){
            throw new ItemTypeNotMatchException(ItemType.PANTS);
        }
        removeItemFromBackpack(equipment, id);
        if(havePantsBefore(equipment)){
            addItemToBackpack(equipment.getPants(), equipment.getBackpack());
        }
        equipment.setPants(id);
        EquipmentModel equipmentModel = Mapper.toModel(equipment);
        return equipmentRepository.save(equipmentModel);
    }

    private boolean havePantsBefore(Equipment equipment) {
        return equipment.getPants() != null;
    }

    private boolean isNotPants(ItemDto itemDto) {
        return !itemDto.type().equals(ItemType.PANTS);
    }

    public EquipmentModel setWeapon(String keycloakId, Long id){
        Equipment equipment = getEquipmentByKeycloakId(keycloakId);
        ItemDto itemDto = getItem(id);
        if(isNotWeapon(itemDto)){
            throw new ItemTypeNotMatchException(ItemType.WEAPON);
        }
        removeItemFromBackpack(equipment, id);
        if(haveWeaponBefore(equipment)){
            addItemToBackpack(equipment.getWeapon(), equipment.getBackpack());
        }
        equipment.setWeapon(id);
        EquipmentModel equipmentModel = Mapper.toModel(equipment);
        return equipmentRepository.save(equipmentModel);
    }

    private boolean haveWeaponBefore(Equipment equipment) {
        return equipment.getWeapon() != null;
    }

    private boolean isNotWeapon(ItemDto itemDto) {
        return !itemDto.type().equals(ItemType.WEAPON);
    }

    public EquipmentModel setShield(String keycloakId, Long id){
        Equipment equipment = getEquipmentByKeycloakId(keycloakId);
        ItemDto itemDto = getItem(id);
        if(isNotShield(itemDto)){
            throw new ItemTypeNotMatchException(ItemType.SHIELD);
        }
        removeItemFromBackpack(equipment, id);
        if(haveShieldBefore(equipment)){
            addItemToBackpack(equipment.getShield(), equipment.getBackpack());
        }
        equipment.setShield(id);
        EquipmentModel equipmentModel = Mapper.toModel(equipment);
        return equipmentRepository.save(equipmentModel);
    }

    private boolean haveShieldBefore(Equipment equipment) {
        return equipment.getShield() != null;
    }

    private boolean isNotShield(ItemDto itemDto) {
        return !itemDto.type().equals(ItemType.SHIELD);
    }

    public EquipmentModel addItemToBackpack(String keycloakId, long id){
        if(isNotItem(id)){
            throw new ItemNotFoundException(id);
        }
        Equipment equipment = getEquipmentByKeycloakId(keycloakId);
        addItemToBackpack(id, equipment.getBackpack());
        EquipmentModel equipmentModel = Mapper.toModel(equipment);
        return equipmentRepository.save(equipmentModel);
    }

    private boolean isNotItem(long id) {
        return !itemService.isItem(id);
    }

    private void addItemToBackpack(long id, List<BackpackItem> backpack) {
        if(isBackpackFull(backpack)){
            throw new BackpackIsFullException();
        }
        backpack.add(buildBackpackItem(id));
    }

    private BackpackItem buildBackpackItem(long id) {
        return BackpackItem.Builder.aBackpackItem()
                .itemId(id)
                .build();
    }

    private boolean isBackpackFull(List<BackpackItem> backpack) {
        return backpack.size() == backpackConfig.getSize();
    }

    public EquipmentModel removeItemFromBackpack(String keycloakId, long id){
        if (isNotItem(id)){
            throw new ItemNotFoundException(id);
        }
        Equipment equipment = getEquipmentByKeycloakId(keycloakId);
        removeItemFromBackpack(equipment, id);
        EquipmentModel equipmentModel = Mapper.toModel(equipment);
        return equipmentRepository.save(equipmentModel);
    }
}
