package com.lastcivilization.equipmentwriteservice.domain.port;

import com.lastcivilization.equipmentwriteservice.domain.view.EquipmentModel;

public interface EquipmentRepositoryPort {

    EquipmentModel save(EquipmentModel equipmentModel);
    void deleteById(long id);
}
