package com.lastcivilization.equipmentwriteservice.infrastructure.application.rest;

import com.lastcivilization.equipmentwriteservice.utils.IntegrationBaseClass;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class EquipmentControllerTest extends IntegrationBaseClass {

    @Test
    void shouldCreateEquipment() throws Exception {
        //given
        //when
        ResultActions createResult = mockMvc.perform(post("/equipments"));
        //then
        createResult.andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.helmet").isEmpty())
                .andExpect(jsonPath("$.armor").isEmpty())
                .andExpect(jsonPath("$.shoes").isEmpty())
                .andExpect(jsonPath("$.pants").isEmpty())
                .andExpect(jsonPath("$.weapon").isEmpty())
                .andExpect(jsonPath("$.shield").isEmpty())
                .andExpect(jsonPath("$.backpack").isArray());
    }

    @Test
    void shouldSetHelmet() throws Exception {
        //given
        //when
        ResultActions setResult = mockMvc.perform(put("/equipments/test/helmets/1"));
        //then
        setResult.andExpect(status().isOk())
                .andExpect(jsonPath("$.helmet").value(1L));
    }

    @Test
    void removeHelmet() {
        //given
        //when
        //then
    }

    @Test
    void setArmor() {
        //given
        //when
        //then
    }

    @Test
    void removeArmor() {
        //given
        //when
        //then
    }

    @Test
    void setShoes() {
        //given
        //when
        //then
    }

    @Test
    void removeShoes() {
        //given
        //when
        //then
    }

    @Test
    void setPants() {
        //given
        //when
        //then
    }

    @Test
    void removePants() {
        //given
        //when
        //then
    }

    @Test
    void setWeapon() {
        //given
        //when
        //then
    }

    @Test
    void removeWeapom() {
        //given
        //when
        //then
    }

    @Test
    void setShield() {
        //given
        //when
        //then
    }

    @Test
    void removeShield() {
        //given
        //when
        //then
    }

    @Test
    void addItemToBackpack() {
        //given
        //when
        //then
    }

    @Test
    void removeItemFromBackpack() {
        //given
        //when
        //then
    }
}