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
    void setArmor() throws Exception {
        //given
        //when
        ResultActions setResult = mockMvc.perform(put("/equipments/test/armors/2"));
        //then
        setResult.andExpect(status().isOk())
                .andExpect(jsonPath("$.armor").value(2L));
    }

    @Test
    void setShoes() throws Exception {
        //given
        //when
        ResultActions setResult = mockMvc.perform(put("/equipments/test/shoes/3"));
        //then
        setResult.andExpect(status().isOk())
                .andExpect(jsonPath("$.shoes").value(3L));
    }

    @Test
    void setPants() throws Exception {
        //given
        //when
        ResultActions setResult = mockMvc.perform(put("/equipments/test/pants/4"));
        //then
        setResult.andExpect(status().isOk())
                .andExpect(jsonPath("$.pants").value(4L));
    }

    @Test
    void setWeapon() throws Exception {
        //given
        //when
        ResultActions setResult = mockMvc.perform(put("/equipments/test/weapons/5"));
        //then
        setResult.andExpect(status().isOk())
                .andExpect(jsonPath("$.weapon").value(5L));
    }

    @Test
    void setShield() throws Exception {
        //given
        //when
        ResultActions setResult = mockMvc.perform(put("/equipments/test/shields/6"));
        //then
        setResult.andExpect(status().isOk())
                .andExpect(jsonPath("$.shield").value(6L));
    }

    @Test
    void addItemToBackpack() throws Exception {
        //given
        //when
        ResultActions addResult = mockMvc.perform(put("/equipments/test/backpacks/2"));
        //then
        addResult.andExpect(status().isOk())
                .andExpect(jsonPath("$.backpack.[1].itemId").value(2L));
    }

    @Test
    void removeItemFromBackpack() throws Exception {
        //given
        //when
        ResultActions addResult = mockMvc.perform(put("/equipments/test/backpacks/1/remove"));
        //then
        addResult.andExpect(status().isOk())
                .andExpect(jsonPath("$.backpack.[0].itemId").exists())
                .andExpect(jsonPath("$.backpack.[1].itemId").exists())
                .andExpect(jsonPath("$.backpack.[2].itemId").exists())
                .andExpect(jsonPath("$.backpack.[3].itemId").exists())
                .andExpect(jsonPath("$.backpack.[4].itemId").exists())
                .andExpect(jsonPath("$.backpack.[5].itemId").doesNotExist());
    }
}