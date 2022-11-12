package com.lastcivilization.equipmentwriteservice.infrastructure.application.rest;

import com.lastcivilization.equipmentwriteservice.domain.EquipmentService;
import com.lastcivilization.equipmentwriteservice.domain.view.EquipmentModel;
import com.lastcivilization.equipmentwriteservice.infrastructure.application.rest.dto.EquipmentDto;
import liquibase.pro.packaged.M;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

import static com.lastcivilization.equipmentwriteservice.infrastructure.application.rest.RestMapper.MAPPER;

@RestController
@RequiredArgsConstructor
@RequestMapping("/equipments")
@Validated
class EquipmentController {

    private final EquipmentService equipmentService;

    @PostMapping
    EquipmentDto createEquipment(){
        EquipmentModel equipmentModel = equipmentService.createEquipment();
        return MAPPER.toDto(equipmentModel);
    }

    @PutMapping("/{keycloakId}/helmets/{id}")
    EquipmentDto setHelmet(@PathVariable String keycloakId, @PathVariable @Min(1) long id){
        EquipmentModel equipmentModel = equipmentService.setHelmet(keycloakId, id);
        return MAPPER.toDto(equipmentModel);
    }

    @PutMapping("/{keycloakId}/helmets")
    EquipmentDto removeHelmet(@PathVariable String keycloakId){
        EquipmentModel equipmentModel = equipmentService.setHelmet(keycloakId, null);
        return MAPPER.toDto(equipmentModel);
    }

    @PutMapping("/{keycloakId}/armors/{id}")
    EquipmentDto setArmor(@PathVariable String keycloakId, @PathVariable @Min(1) long id){
        EquipmentModel equipmentModel = equipmentService.setArmor(keycloakId, id);
        return MAPPER.toDto(equipmentModel);
    }

    @PutMapping("/{keycloakId}/armors")
    EquipmentDto removeArmor(@PathVariable String keycloakId){
        EquipmentModel equipmentModel = equipmentService.setArmor(keycloakId, null);
        return MAPPER.toDto(equipmentModel);
    }

    @PutMapping("/{keycloakId}/shoes/{id}")
    EquipmentDto setShoes(@PathVariable String keycloakId, @PathVariable @Min(1) long id){
        EquipmentModel equipmentModel = equipmentService.setShoes(keycloakId, id);
        return MAPPER.toDto(equipmentModel);
    }

    @PutMapping("/{keycloakId}/shoes")
    EquipmentDto removeShoes(@PathVariable String keycloakId){
        EquipmentModel equipmentModel = equipmentService.setShoes(keycloakId, null);
        return MAPPER.toDto(equipmentModel);
    }

    @PutMapping("/{keycloakId}/pants/{id}")
    EquipmentDto setPants(@PathVariable String keycloakId, @PathVariable @Min(1) long id){
        EquipmentModel equipmentModel = equipmentService.setPants(keycloakId, id);
        return MAPPER.toDto(equipmentModel);
    }

    @PutMapping("/{keycloakId}/pants")
    EquipmentDto removePants(@PathVariable String keycloakId){
        EquipmentModel equipmentModel = equipmentService.setPants(keycloakId, null);
        return MAPPER.toDto(equipmentModel);
    }

    @PutMapping("/{keycloakId}/weapons/{id}")
    EquipmentDto setWeapon(@PathVariable String keycloakId, @PathVariable @Min(1) long id){
        EquipmentModel equipmentModel = equipmentService.setWeapon(keycloakId, id);
        return MAPPER.toDto(equipmentModel);
    }

    @PutMapping("/{keycloakId}/weapons")
    EquipmentDto removeWeapom(@PathVariable String keycloakId){
        EquipmentModel equipmentModel = equipmentService.setWeapon(keycloakId, null);
        return MAPPER.toDto(equipmentModel);
    }

    @PutMapping("/{keycloakId}/shields/{id}")
    EquipmentDto setShield(@PathVariable String keycloakId, @PathVariable @Min(1) long id){
        EquipmentModel equipmentModel = equipmentService.setShield(keycloakId, id);
        return MAPPER.toDto(equipmentModel);
    }

    @PutMapping("/{keycloakId}/shields")
    EquipmentDto removeShield(@PathVariable String keycloakId){
        EquipmentModel equipmentModel = equipmentService.setShield(keycloakId, null);
        return MAPPER.toDto(equipmentModel);
    }

    @PutMapping("/{keycloakId}/backpacks/{id}")
    EquipmentDto addItemToBackpack(@PathVariable String keycloakId, @PathVariable @Min(1) long id){
        EquipmentModel equipmentModel = equipmentService.addItemToBackpack(keycloakId, id);
        return MAPPER.toDto(equipmentModel);
    }

    @PutMapping("/{keycloakId}/backpacks/{id}/remove")
    EquipmentDto removeItemFromBackpack(@PathVariable String keycloakId, @PathVariable @Min(1) long id){
        EquipmentModel equipmentModel = equipmentService.removeItemFromBackpack(keycloakId, id);
        return MAPPER.toDto(equipmentModel);
    }
}
