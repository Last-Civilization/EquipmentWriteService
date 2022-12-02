package com.lastcivilization.equipmentwriteservice.infrastructure.application.rest;

import com.lastcivilization.equipmentwriteservice.domain.EquipmentService;
import com.lastcivilization.equipmentwriteservice.domain.view.EquipmentModel;
import com.lastcivilization.equipmentwriteservice.infrastructure.application.rest.dto.EquipmentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequiredArgsConstructor
@RequestMapping("/equipments")
@Validated
class EquipmentController {

    private final EquipmentService equipmentService;
    private final RestMapper restMapper;

    @PostMapping
    @ResponseStatus(CREATED)
    EquipmentDto createEquipment(){
        EquipmentModel equipmentModel = equipmentService.createEquipment();
        return restMapper.toDto(equipmentModel);
    }

    @PutMapping("/{keycloakId}/helmets/{id}")
    EquipmentDto setHelmet(@PathVariable String keycloakId, @PathVariable @Min(1) long id){
        EquipmentModel equipmentModel = equipmentService.setHelmet(keycloakId, id);
        return restMapper.toDto(equipmentModel);
    }

    @PutMapping("/{keycloakId}/armors/{id}")
    EquipmentDto setArmor(@PathVariable String keycloakId, @PathVariable @Min(1) long id){
        EquipmentModel equipmentModel = equipmentService.setArmor(keycloakId, id);
        return restMapper.toDto(equipmentModel);
    }

    @PutMapping("/{keycloakId}/shoes/{id}")
    EquipmentDto setShoes(@PathVariable String keycloakId, @PathVariable @Min(1) long id){
        EquipmentModel equipmentModel = equipmentService.setShoes(keycloakId, id);
        return restMapper.toDto(equipmentModel);
    }

    @PutMapping("/{keycloakId}/pants/{id}")
    EquipmentDto setPants(@PathVariable String keycloakId, @PathVariable @Min(1) long id){
        EquipmentModel equipmentModel = equipmentService.setPants(keycloakId, id);
        return restMapper.toDto(equipmentModel);
    }

    @PutMapping("/{keycloakId}/weapons/{id}")
    EquipmentDto setWeapon(@PathVariable String keycloakId, @PathVariable @Min(1) long id){
        EquipmentModel equipmentModel = equipmentService.setWeapon(keycloakId, id);
        return restMapper.toDto(equipmentModel);
    }

    @PutMapping("/{keycloakId}/shields/{id}")
    EquipmentDto setShield(@PathVariable String keycloakId, @PathVariable @Min(1) long id){
        EquipmentModel equipmentModel = equipmentService.setShield(keycloakId, id);
        return restMapper.toDto(equipmentModel);
    }

    @PutMapping("/{keycloakId}/backpacks/{id}")
    EquipmentDto addItemToBackpack(@PathVariable String keycloakId, @PathVariable @Min(1) long id){
        EquipmentModel equipmentModel = equipmentService.addItemToBackpack(keycloakId, id);
        return restMapper.toDto(equipmentModel);
    }

    @PutMapping("/{keycloakId}/backpacks/{id}/remove")
    EquipmentDto removeItemFromBackpack(@PathVariable String keycloakId, @PathVariable @Min(1) long id){
        EquipmentModel equipmentModel = equipmentService.removeItemFromBackpack(keycloakId, id);
        return restMapper.toDto(equipmentModel);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    void deleteEquipment(@PathVariable @Min(1) long id){
        equipmentService.deleteEquipment(id);
    }
}
