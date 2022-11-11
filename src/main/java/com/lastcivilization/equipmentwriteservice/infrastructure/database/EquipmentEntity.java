package com.lastcivilization.equipmentwriteservice.infrastructure.database;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "equipments")
@Getter
@Setter
class EquipmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long helmet;
    private Long armor;
    private Long shoes;
    private Long pants;
    private Long weapon;
    private Long shield;
    @OneToMany
    @JoinColumn(name = "equipment_id", referencedColumnName = "id")
    @Builder.Default
    private List<BackpackItemEntity> backpack = new java.util.ArrayList<>();
}