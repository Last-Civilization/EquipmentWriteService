package com.lastcivilization.equipmentwriteservice.domain;

import javax.persistence.Column;
import javax.persistence.FetchType;
import java.util.ArrayList;
import java.util.List;

class Equipment {

    private Long id;
    private Long helmet;
    private Long armor;
    private Long shoes;
    private Long pants;
    private Long weapon;
    private Long shield;
    private List<BackpackItem> backpack;

    public Equipment(Long id, Long helmet, Long armor, Long shoes, Long pants, Long weapon, Long shield, List<BackpackItem> backpack) {
        this.id = id;
        this.helmet = helmet;
        this.armor = armor;
        this.shoes = shoes;
        this.pants = pants;
        this.weapon = weapon;
        this.shield = shield;
        this.backpack = backpack;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHelmet() {
        return helmet;
    }

    public void setHelmet(Long helmet) {
        this.helmet = helmet;
    }

    public Long getArmor() {
        return armor;
    }

    public void setArmor(Long armor) {
        this.armor = armor;
    }

    public Long getShoes() {
        return shoes;
    }

    public void setShoes(Long shoes) {
        this.shoes = shoes;
    }

    public Long getPants() {
        return pants;
    }

    public void setPants(Long pants) {
        this.pants = pants;
    }

    public Long getWeapon() {
        return weapon;
    }

    public void setWeapon(Long weapon) {
        this.weapon = weapon;
    }

    public Long getShield() {
        return shield;
    }

    public void setShield(Long shield) {
        this.shield = shield;
    }

    public List<BackpackItem> getBackpack() {
        return backpack;
    }

    public void setBackpack(List<BackpackItem> backpack) {
        this.backpack = backpack;
    }

    public static final class Builder {

        private Long id;
        private Long helmet;
        private Long armor;
        private Long shoes;
        private Long pants;
        private Long weapon;
        private Long shield;
        private List<BackpackItem> backpack = new ArrayList<>();

        private Builder() {
        }

        public static Builder anEquipment() {
            return new Builder();
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder helmet(Long helmet) {
            this.helmet = helmet;
            return this;
        }

        public Builder armor(Long armor) {
            this.armor = armor;
            return this;
        }

        public Builder shoes(Long shoes) {
            this.shoes = shoes;
            return this;
        }

        public Builder pants(Long pants) {
            this.pants = pants;
            return this;
        }

        public Builder weapon(Long weapon) {
            this.weapon = weapon;
            return this;
        }

        public Builder shield(Long shield) {
            this.shield = shield;
            return this;
        }

        public Builder backpack(List<BackpackItem> backpack) {
            this.backpack = backpack;
            return this;
        }

        public Equipment build() {
            return new Equipment(id, helmet, armor, shoes, pants, weapon, shield, backpack);
        }
    }
}
