package com.lastcivilization.equipmentwriteservice.utils;

import com.lastcivilization.equipmentwriteservice.domain.port.EquipmentRepository;
import com.lastcivilization.equipmentwriteservice.domain.view.BackpackItemModel;
import com.lastcivilization.equipmentwriteservice.domain.view.EquipmentModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
class EquipmentCreator {

    private final EquipmentRepository equipmentRepository;

    EquipmentModel createEquipment(){
        EquipmentModel equipmentModel = new EquipmentModel(
                1L,
                null,
                null,
                null,
                null,
                null,
                null,
                List.of(new BackpackItemModel(
                        1L,
                        1L
                ))
        );
        return equipmentRepository.save(equipmentModel);
    }

}
