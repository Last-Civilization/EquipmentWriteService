package com.lastcivilization.equipmentwriteservice.domain;

import com.lastcivilization.equipmentwriteservice.domain.exception.ItemNotFoundException;
import com.lastcivilization.equipmentwriteservice.domain.exception.ItemTypeNotMatchException;
import com.lastcivilization.equipmentwriteservice.domain.exception.UserNotFoundException;
import com.lastcivilization.equipmentwriteservice.domain.port.EquipmentRepository;
import com.lastcivilization.equipmentwriteservice.domain.port.ItemService;
import com.lastcivilization.equipmentwriteservice.domain.port.UserService;
import com.lastcivilization.equipmentwriteservice.domain.port.dto.ItemDto;
import com.lastcivilization.equipmentwriteservice.domain.port.dto.ItemType;
import com.lastcivilization.equipmentwriteservice.domain.port.dto.UserDto;
import com.lastcivilization.equipmentwriteservice.domain.view.BackpackItemModel;
import com.lastcivilization.equipmentwriteservice.domain.view.EquipmentModel;
import com.lastcivilization.equipmentwriteservice.infrastructure.backpack.BackpackConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EquipmentServiceTest {

    @Mock
    EquipmentRepository equipmentRepository;
    @Mock
    UserService userService;
    @Mock
    ItemService itemService;
    @Mock
    BackpackConfig backpackConfig;

    @InjectMocks
    EquipmentService underTest;

    private UserDto userDto = new UserDto(1L);

    @Test
    void shouldCreateEquipment() {
        //given
        doAnswer(invocation -> invocation.getArgument(0)).when(equipmentRepository).save(any(EquipmentModel.class));
        //when
        EquipmentModel equipmentModel = underTest.createEquipment();
        //then
        assertThat(equipmentModel).isNotNull();
    }

    @Test
    void shouldSetHelmetWithoutHelmetBefore() {
        //given
        when(userService.getUser(anyString())).thenReturn(Optional.of(userDto));
        when(equipmentRepository.findById(anyLong())).thenReturn(
                Optional.of(
                        new EquipmentModel(1L, null, null, null, null, null, null,
                                List.of(new BackpackItemModel[]{ new BackpackItemModel(1L, 1L)}))));
        when(itemService.getItem(anyLong())).thenReturn(Optional.of(new ItemDto(ItemType.HELMET)));
        doAnswer(invocation -> invocation.getArgument(0)).when(equipmentRepository).save(any(EquipmentModel.class));
        //when
        EquipmentModel equipmentModel = underTest.setHelmet(anyString(), 1L);
        //then
        assertThat(equipmentModel.helmet()).isEqualTo(1L);
        assertThat(equipmentModel.backpack().size()).isEqualTo(0);
    }

    @Test
    void shouldSetHelmetWithHelmetBefore() {
        //given
        when(userService.getUser(anyString())).thenReturn(Optional.of(userDto));
        when(equipmentRepository.findById(anyLong())).thenReturn(
                Optional.of(
                        new EquipmentModel(1L, 1L, null, null, null, null, null,
                                List.of(new BackpackItemModel[]{ new BackpackItemModel(1L, 1L)}))));
        when(itemService.getItem(anyLong())).thenReturn(Optional.of(new ItemDto(ItemType.HELMET)));
        doAnswer(invocation -> invocation.getArgument(0)).when(equipmentRepository).save(any(EquipmentModel.class));
        when(backpackConfig.getSize()).thenReturn(1);
        //when
        EquipmentModel equipmentModel = underTest.setHelmet(anyString(), 1L);
        //then
        assertThat(equipmentModel.helmet()).isEqualTo(1L);
        assertThat(equipmentModel.backpack().size()).isEqualTo(1);
    }

    @Test
    void shouldThrowItemTypeNotMatchWhileSettingHelmet() {
        //given
        when(userService.getUser(anyString())).thenReturn(Optional.of(userDto));
        when(equipmentRepository.findById(anyLong())).thenReturn(
                Optional.of(
                        new EquipmentModel(1L, 1L, null, null, null, null, null,
                                List.of(new BackpackItemModel[]{ new BackpackItemModel(1L, 1L)}))));
        when(itemService.getItem(anyLong())).thenReturn(Optional.of(new ItemDto(ItemType.USE)));
        //when
        Executable setExecutable = () -> underTest.setHelmet(anyString(), 1L);
        //then
        assertThrows(ItemTypeNotMatchException.class, setExecutable);
    }

    @Test
    void shouldThrowItemNotFoundWhileSettingHelmet() {
        //given
        when(userService.getUser(anyString())).thenReturn(Optional.of(userDto));
        when(equipmentRepository.findById(anyLong())).thenReturn(
                Optional.of(
                        new EquipmentModel(1L, 1L, null, null, null, null, null,
                                List.of(new BackpackItemModel[]{ new BackpackItemModel(1L, 1L)}))));
        when(itemService.getItem(anyLong())).thenReturn(Optional.empty());
        //when
        Executable setExecutable = () -> underTest.setHelmet(anyString(), 1L);
        //then
        assertThrows(ItemNotFoundException.class, setExecutable);
    }

    @Test
    void shouldThrowUserNotFoundWhileSettingHelmet() {
        //given
        when(userService.getUser(anyString())).thenReturn(Optional.empty());
        //when
        Executable setExecutable = () -> underTest.setHelmet(anyString(), 1L);
        //then
        assertThrows(UserNotFoundException.class, setExecutable);
    }

    @Test
    void setArmor() {
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
    void setPants() {
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
    void setShield() {
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