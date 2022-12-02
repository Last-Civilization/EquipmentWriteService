package com.lastcivilization.equipmentwriteservice.infrastructure.database;

import com.lastcivilization.equipmentwriteservice.domain.port.EquipmentRepository;
import com.lastcivilization.equipmentwriteservice.domain.view.EquipmentModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class EquipmentRepositoryAdapter implements EquipmentRepository {

    private final EquipmentJpaRepository equipmentJpaRepository;
    private final EntityMapper entityMapper;

    @Override
    public EquipmentModel save(EquipmentModel equipmentModel) {
        EquipmentEntity equipmentEntity = entityMapper.toEntity(equipmentModel);
        EquipmentEntity savedEquipmentEntity = equipmentJpaRepository.save(equipmentEntity);
        return entityMapper.toModel(savedEquipmentEntity);
    }

    @Override
    public void deleteById(long id) {
        equipmentJpaRepository.deleteById(id);
    }

    @Override
    public Optional<EquipmentModel> findById(long id) {
        Optional<EquipmentEntity> equipmentEntityOptional = equipmentJpaRepository.findById(id);
        return equipmentEntityOptional
                .map(entityMapper::toModel);
    }
}
