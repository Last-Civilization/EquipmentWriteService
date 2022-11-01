package com.lastcivilization.equipmentwriteservice.domain;

import com.lastcivilization.equipmentwriteservice.domain.port.EquipmentRepositoryPort;
import com.lastcivilization.equipmentwriteservice.domain.view.EquipmentModel;

public class EquipmentService {

    private final EquipmentRepositoryPort equipmentRepository;

    EquipmentService(EquipmentRepositoryPort equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public EquipmentModel createEquipment(){
        Equipment equipment = Equipment.Builder.anEquipment().build();
        EquipmentModel equipmentModel = Mapper.toModel(equipment);
        return equipmentRepository.save(equipmentModel);
    }

    public void deleteEquipment(long id){
        equipmentRepository.deleteById(id);
    }
}
