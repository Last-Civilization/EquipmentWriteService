package com.lastcivilization.equipmentwriteservice.domain;

import com.lastcivilization.equipmentwriteservice.domain.exception.EquipmentNotFoundException;
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

    public EquipmentModel setHelmet(long id){
        Equipment equipment = getEquipment(id);
        equipment.setHelmet(id);
        EquipmentModel equipmentModel = Mapper.toModel(equipment);
        return equipmentRepository.save(equipmentModel);
    }

    private Equipment getEquipment(long id){
        EquipmentModel equipmentModel = getEquipmentModel(id);
        return Mapper.toDomain(equipmentModel);
    }

    private EquipmentModel getEquipmentModel(long id) {
        return equipmentRepository.findById(id)
                .orElseThrow(() -> new EquipmentNotFoundException(id));
    }
}
