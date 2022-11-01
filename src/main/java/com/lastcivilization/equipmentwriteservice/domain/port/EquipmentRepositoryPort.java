package com.lastcivilization.equipmentwriteservice.domain.port;

import com.lastcivilization.equipmentwriteservice.domain.view.EquipmentModel;

import java.util.Optional;

public interface EquipmentRepositoryPort {

    EquipmentModel save(EquipmentModel equipmentModel);
    void deleteById(long id);

    Optional<EquipmentModel> findById(long id);
}
