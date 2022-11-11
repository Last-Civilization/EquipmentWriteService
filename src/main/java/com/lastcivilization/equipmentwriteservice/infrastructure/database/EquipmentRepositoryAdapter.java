package com.lastcivilization.equipmentwriteservice.infrastructure.database;

import com.lastcivilization.equipmentwriteservice.domain.port.EquipmentRepository;
import com.lastcivilization.equipmentwriteservice.domain.view.EquipmentModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.lastcivilization.equipmentwriteservice.infrastructure.database.EntityMapper.MAPPER;

@Service
@RequiredArgsConstructor
class EquipmentRepositoryAdapter implements EquipmentRepository {

    private final EquipmentJpaRepository equipmentJpaRepository;

    @Override
    public EquipmentModel save(EquipmentModel equipmentModel) {
        EquipmentEntity equipmentEntity = MAPPER.toEntity(equipmentModel);
        EquipmentEntity savedEquipmentEntity = equipmentJpaRepository.save(equipmentEntity);
        return MAPPER.toModel(savedEquipmentEntity);
    }

    @Override
    public void deleteById(long id) {
        equipmentJpaRepository.deleteById(id);
    }

    @Override
    public Optional<EquipmentModel> findById(long id) {
        Optional<EquipmentEntity> equipmentEntityOptional = equipmentJpaRepository.findById(id);
        return equipmentEntityOptional
                .map(MAPPER::toModel);
    }
}
