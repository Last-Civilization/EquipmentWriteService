package com.lastcivilization.equipmentwriteservice.utils;

import com.lastcivilization.equipmentwriteservice.domain.port.EquipmentRepository;
import com.lastcivilization.equipmentwriteservice.domain.view.BackpackItemModel;
import com.lastcivilization.equipmentwriteservice.domain.view.EquipmentModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class EquipmentCreator {

    private final EquipmentRepository equipmentRepository;

    EquipmentModel createEquipment(){
        EquipmentModel equipmentModel = new EquipmentModel(
                1L,
                1L,
                1L,
                1L,
                1L,
                1L,
                1L,
                List.of(new BackpackItemModel(
                        null,
                        1L
                        ),
                        new BackpackItemModel(
                                null,
                                2L
                        ),
                        new BackpackItemModel(
                                null,
                                3L
                        ),
                        new BackpackItemModel(
                                null,
                                4L
                        ),
                        new BackpackItemModel(
                                null,
                                5L
                        ),
                        new BackpackItemModel(
                                null,
                                6L
                        ))
        );
        return equipmentRepository.save(equipmentModel);
    }

}
