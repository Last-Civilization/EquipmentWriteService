package com.lastcivilization.equipmentwriteservice.domain;

import com.lastcivilization.equipmentwriteservice.domain.exception.BackpackIsFullException;
import com.lastcivilization.equipmentwriteservice.domain.exception.EquipmentNotFoundException;
import com.lastcivilization.equipmentwriteservice.domain.exception.ItemNotFoundException;
import com.lastcivilization.equipmentwriteservice.domain.exception.ItemNotInBackpackException;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;
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
                                List.of(new BackpackItemModel(1L, 1L)))));
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
                                List.of(new BackpackItemModel(1L, 1L)))));
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
                        new EquipmentModel(1L, null, null, null, null, null, null,
                                List.of(new BackpackItemModel(1L, 1L)))));
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
                        new EquipmentModel(1L, null, null, null, null, null, null,
                                List.of(new BackpackItemModel(1L, 1L)))));
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
    void shouldThrowItemNotInBackpackWhileSettingHelmet() {
        //given
        when(userService.getUser(anyString())).thenReturn(Optional.of(userDto));
        when(equipmentRepository.findById(anyLong())).thenReturn(
                Optional.of(
                        new EquipmentModel(1L, null, null, null, null, null, null,
                                new ArrayList<>())));
        when(itemService.getItem(anyLong())).thenReturn(Optional.of(new ItemDto(ItemType.HELMET)));
        //when
        Executable setExecutable = () -> underTest.setHelmet(anyString(), 1L);
        //then
        assertThrows(ItemNotInBackpackException.class, setExecutable);
    }

    @Test
    void shouldReturnEquipmentNotFoundExceptionWhileSettingHelmet() {
        //given
        when(userService.getUser(anyString())).thenReturn(Optional.of(userDto));
        when(equipmentRepository.findById(anyLong())).thenReturn(Optional.empty());
        //when
        Executable setExecutable = () -> underTest.setHelmet(anyString(), 1L);
        //then
        assertThrows(EquipmentNotFoundException.class, setExecutable);
    }

    @Test
    void shouldSetArmorWithoutArmorBefore() {
        //given
        when(userService.getUser(anyString())).thenReturn(Optional.of(userDto));
        when(equipmentRepository.findById(anyLong())).thenReturn(
                Optional.of(
                        new EquipmentModel(1L, null, null, null, null, null, null,
                                List.of(new BackpackItemModel(1L, 1L)))));
        when(itemService.getItem(anyLong())).thenReturn(Optional.of(new ItemDto(ItemType.ARMOR)));
        doAnswer(invocation -> invocation.getArgument(0)).when(equipmentRepository).save(any(EquipmentModel.class));
        //when
        EquipmentModel equipmentModel = underTest.setArmor(anyString(), 1L);
        //then
        assertThat(equipmentModel.armor()).isEqualTo(1L);
        assertThat(equipmentModel.backpack().size()).isEqualTo(0);
    }

    @Test
    void shouldSetArmorWithArmorBefore() {
        //given
        when(userService.getUser(anyString())).thenReturn(Optional.of(userDto));
        when(equipmentRepository.findById(anyLong())).thenReturn(
                Optional.of(
                        new EquipmentModel(1L, null, 1L, null, null, null, null,
                                List.of(new BackpackItemModel(1L, 1L)))));
        when(itemService.getItem(anyLong())).thenReturn(Optional.of(new ItemDto(ItemType.ARMOR)));
        doAnswer(invocation -> invocation.getArgument(0)).when(equipmentRepository).save(any(EquipmentModel.class));
        when(backpackConfig.getSize()).thenReturn(1);
        //when
        EquipmentModel equipmentModel = underTest.setArmor(anyString(), 1L);
        //then
        assertThat(equipmentModel.armor()).isEqualTo(1L);
        assertThat(equipmentModel.backpack().size()).isEqualTo(1);
    }

    @Test
    void shouldThrowItemTypeNotMatchWhileSettingArmor() {
        //given
        when(userService.getUser(anyString())).thenReturn(Optional.of(userDto));
        when(equipmentRepository.findById(anyLong())).thenReturn(
                Optional.of(
                        new EquipmentModel(1L, null, null, null, null, null, null,
                                List.of(new BackpackItemModel(1L, 1L)))));
        when(itemService.getItem(anyLong())).thenReturn(Optional.of(new ItemDto(ItemType.USE)));
        //when
        Executable setExecutable = () -> underTest.setArmor(anyString(), 1L);
        //then
        assertThrows(ItemTypeNotMatchException.class, setExecutable);
    }

    @Test
    void shouldThrowItemNotFoundWhileSettingArmor() {
        //given
        when(userService.getUser(anyString())).thenReturn(Optional.of(userDto));
        when(equipmentRepository.findById(anyLong())).thenReturn(
                Optional.of(
                        new EquipmentModel(1L, null, null, null, null, null, null,
                                List.of(new BackpackItemModel(1L, 1L)))));
        when(itemService.getItem(anyLong())).thenReturn(Optional.empty());
        //when
        Executable setExecutable = () -> underTest.setArmor(anyString(), 1L);
        //then
        assertThrows(ItemNotFoundException.class, setExecutable);
    }

    @Test
    void shouldThrowUserNotFoundWhileSettingArmor() {
        //given
        when(userService.getUser(anyString())).thenReturn(Optional.empty());
        //when
        Executable setExecutable = () -> underTest.setArmor(anyString(), 1L);
        //then
        assertThrows(UserNotFoundException.class, setExecutable);
    }

    @Test
    void shouldThrowItemNotInBackpackWhileSettingArmor() {
        //given
        when(userService.getUser(anyString())).thenReturn(Optional.of(userDto));
        when(equipmentRepository.findById(anyLong())).thenReturn(
                Optional.of(
                        new EquipmentModel(1L, null, null, null, null, null, null,
                                new ArrayList<>())));
        when(itemService.getItem(anyLong())).thenReturn(Optional.of(new ItemDto(ItemType.ARMOR)));
        //when
        Executable setExecutable = () -> underTest.setArmor(anyString(), 1L);
        //then
        assertThrows(ItemNotInBackpackException.class, setExecutable);
    }

    @Test
    void shouldReturnEquipmentNotFoundExceptionWhileSettingArmor() {
        //given
        when(userService.getUser(anyString())).thenReturn(Optional.of(userDto));
        when(equipmentRepository.findById(anyLong())).thenReturn(Optional.empty());
        //when
        Executable setExecutable = () -> underTest.setArmor(anyString(), 1L);
        //then
        assertThrows(EquipmentNotFoundException.class, setExecutable);
    }

    @Test
    void shouldSetShoesWithoutShoesBefore() {
        //given
        when(userService.getUser(anyString())).thenReturn(Optional.of(userDto));
        when(equipmentRepository.findById(anyLong())).thenReturn(
                Optional.of(
                        new EquipmentModel(1L, null, null, null, null, null, null,
                                List.of(new BackpackItemModel(1L, 1L)))));
        when(itemService.getItem(anyLong())).thenReturn(Optional.of(new ItemDto(ItemType.SHOES)));
        doAnswer(invocation -> invocation.getArgument(0)).when(equipmentRepository).save(any(EquipmentModel.class));
        //when
        EquipmentModel equipmentModel = underTest.setShoes(anyString(), 1L);
        //then
        assertThat(equipmentModel.shoes()).isEqualTo(1L);
        assertThat(equipmentModel.backpack().size()).isEqualTo(0);
    }

    @Test
    void shouldSetShoesWithShoesBefore() {
        //given
        when(userService.getUser(anyString())).thenReturn(Optional.of(userDto));
        when(equipmentRepository.findById(anyLong())).thenReturn(
                Optional.of(
                        new EquipmentModel(1L, null, null, 1L, null, null, null,
                                List.of(new BackpackItemModel(1L, 1L)))));
        when(itemService.getItem(anyLong())).thenReturn(Optional.of(new ItemDto(ItemType.SHOES)));
        doAnswer(invocation -> invocation.getArgument(0)).when(equipmentRepository).save(any(EquipmentModel.class));
        when(backpackConfig.getSize()).thenReturn(1);
        //when
        EquipmentModel equipmentModel = underTest.setShoes(anyString(), 1L);
        //then
        assertThat(equipmentModel.shoes()).isEqualTo(1L);
        assertThat(equipmentModel.backpack().size()).isEqualTo(1);
    }

    @Test
    void shouldThrowItemTypeNotMatchWhileSettingShoes() {
        //given
        when(userService.getUser(anyString())).thenReturn(Optional.of(userDto));
        when(equipmentRepository.findById(anyLong())).thenReturn(
                Optional.of(
                        new EquipmentModel(1L, null, null, null, null, null, null,
                                List.of(new BackpackItemModel(1L, 1L)))));
        when(itemService.getItem(anyLong())).thenReturn(Optional.of(new ItemDto(ItemType.USE)));
        //when
        Executable setExecutable = () -> underTest.setShoes(anyString(), 1L);
        //then
        assertThrows(ItemTypeNotMatchException.class, setExecutable);
    }

    @Test
    void shouldThrowItemNotFoundWhileSettingShoes() {
        //given
        when(userService.getUser(anyString())).thenReturn(Optional.of(userDto));
        when(equipmentRepository.findById(anyLong())).thenReturn(
                Optional.of(
                        new EquipmentModel(1L, null, null, null, null, null, null,
                                List.of(new BackpackItemModel(1L, 1L)))));
        when(itemService.getItem(anyLong())).thenReturn(Optional.empty());
        //when
        Executable setExecutable = () -> underTest.setShoes(anyString(), 1L);
        //then
        assertThrows(ItemNotFoundException.class, setExecutable);
    }

    @Test
    void shouldThrowUserNotFoundWhileSettingShoes() {
        //given
        when(userService.getUser(anyString())).thenReturn(Optional.empty());
        //when
        Executable setExecutable = () -> underTest.setShoes(anyString(), 1L);
        //then
        assertThrows(UserNotFoundException.class, setExecutable);
    }

    @Test
    void shouldThrowItemNotInBackpackWhileSettingShoes() {
        //given
        when(userService.getUser(anyString())).thenReturn(Optional.of(userDto));
        when(equipmentRepository.findById(anyLong())).thenReturn(
                Optional.of(
                        new EquipmentModel(1L, null, null, null, null, null, null,
                                new ArrayList<>())));
        when(itemService.getItem(anyLong())).thenReturn(Optional.of(new ItemDto(ItemType.SHOES)));
        //when
        Executable setExecutable = () -> underTest.setShoes(anyString(), 1L);
        //then
        assertThrows(ItemNotInBackpackException.class, setExecutable);
    }

    @Test
    void shouldReturnEquipmentNotFoundExceptionWhileSettingShoes() {
        //given
        when(userService.getUser(anyString())).thenReturn(Optional.of(userDto));
        when(equipmentRepository.findById(anyLong())).thenReturn(Optional.empty());
        //when
        Executable setExecutable = () -> underTest.setShoes(anyString(), 1L);
        //then
        assertThrows(EquipmentNotFoundException.class, setExecutable);
    }

    @Test
    void shouldSetPantsWithoutPantsBefore() {
        //given
        when(userService.getUser(anyString())).thenReturn(Optional.of(userDto));
        when(equipmentRepository.findById(anyLong())).thenReturn(
                Optional.of(
                        new EquipmentModel(1L, null, null, null, null, null, null,
                                List.of(new BackpackItemModel(1L, 1L)))));
        when(itemService.getItem(anyLong())).thenReturn(Optional.of(new ItemDto(ItemType.PANTS)));
        doAnswer(invocation -> invocation.getArgument(0)).when(equipmentRepository).save(any(EquipmentModel.class));
        //when
        EquipmentModel equipmentModel = underTest.setPants(anyString(), 1L);
        //then
        assertThat(equipmentModel.pants()).isEqualTo(1L);
        assertThat(equipmentModel.backpack().size()).isEqualTo(0);
    }

    @Test
    void shouldSetPantsWithPantsBefore() {
        //given
        when(userService.getUser(anyString())).thenReturn(Optional.of(userDto));
        when(equipmentRepository.findById(anyLong())).thenReturn(
                Optional.of(
                        new EquipmentModel(1L, null, null, null, 1L, null, null,
                                List.of(new BackpackItemModel(1L, 1L)))));
        when(itemService.getItem(anyLong())).thenReturn(Optional.of(new ItemDto(ItemType.PANTS)));
        doAnswer(invocation -> invocation.getArgument(0)).when(equipmentRepository).save(any(EquipmentModel.class));
        when(backpackConfig.getSize()).thenReturn(1);
        //when
        EquipmentModel equipmentModel = underTest.setPants(anyString(), 1L);
        //then
        assertThat(equipmentModel.pants()).isEqualTo(1L);
        assertThat(equipmentModel.backpack().size()).isEqualTo(1);
    }

    @Test
    void shouldThrowItemTypeNotMatchWhileSettingPants() {
        //given
        when(userService.getUser(anyString())).thenReturn(Optional.of(userDto));
        when(equipmentRepository.findById(anyLong())).thenReturn(
                Optional.of(
                        new EquipmentModel(1L, null, null, null, null, null, null,
                                List.of(new BackpackItemModel(1L, 1L)))));
        when(itemService.getItem(anyLong())).thenReturn(Optional.of(new ItemDto(ItemType.USE)));
        //when
        Executable setExecutable = () -> underTest.setPants(anyString(), 1L);
        //then
        assertThrows(ItemTypeNotMatchException.class, setExecutable);
    }

    @Test
    void shouldThrowItemNotFoundWhileSettingPants() {
        //given
        when(userService.getUser(anyString())).thenReturn(Optional.of(userDto));
        when(equipmentRepository.findById(anyLong())).thenReturn(
                Optional.of(
                        new EquipmentModel(1L, null, null, null, null, null, null,
                                List.of(new BackpackItemModel(1L, 1L)))));
        when(itemService.getItem(anyLong())).thenReturn(Optional.empty());
        //when
        Executable setExecutable = () -> underTest.setPants(anyString(), 1L);
        //then
        assertThrows(ItemNotFoundException.class, setExecutable);
    }

    @Test
    void shouldThrowUserNotFoundWhileSettingPants() {
        //given
        when(userService.getUser(anyString())).thenReturn(Optional.empty());
        //when
        Executable setExecutable = () -> underTest.setPants(anyString(), 1L);
        //then
        assertThrows(UserNotFoundException.class, setExecutable);
    }

    @Test
    void shouldThrowItemNotInBackpackWhileSettingPants() {
        //given
        when(userService.getUser(anyString())).thenReturn(Optional.of(userDto));
        when(equipmentRepository.findById(anyLong())).thenReturn(
                Optional.of(
                        new EquipmentModel(1L, null, null, null, null, null, null,
                                new ArrayList<>())));
        when(itemService.getItem(anyLong())).thenReturn(Optional.of(new ItemDto(ItemType.PANTS)));
        //when
        Executable setExecutable = () -> underTest.setPants(anyString(), 1L);
        //then
        assertThrows(ItemNotInBackpackException.class, setExecutable);
    }

    @Test
    void shouldReturnEquipmentNotFoundExceptionWhileSettingPants() {
        //given
        when(userService.getUser(anyString())).thenReturn(Optional.of(userDto));
        when(equipmentRepository.findById(anyLong())).thenReturn(Optional.empty());
        //when
        Executable setExecutable = () -> underTest.setPants(anyString(), 1L);
        //then
        assertThrows(EquipmentNotFoundException.class, setExecutable);
    }

    @Test
    void shouldSetWeaponWithoutWeaponBefore() {
        //given
        when(userService.getUser(anyString())).thenReturn(Optional.of(userDto));
        when(equipmentRepository.findById(anyLong())).thenReturn(
                Optional.of(
                        new EquipmentModel(1L, null, null, null, null, null, null,
                                List.of(new BackpackItemModel(1L, 1L)))));
        when(itemService.getItem(anyLong())).thenReturn(Optional.of(new ItemDto(ItemType.WEAPON)));
        doAnswer(invocation -> invocation.getArgument(0)).when(equipmentRepository).save(any(EquipmentModel.class));
        //when
        EquipmentModel equipmentModel = underTest.setWeapon(anyString(), 1L);
        //then
        assertThat(equipmentModel.weapon()).isEqualTo(1L);
        assertThat(equipmentModel.backpack().size()).isEqualTo(0);
    }

    @Test
    void shouldSetWeaponWithWeaponBefore() {
        //given
        when(userService.getUser(anyString())).thenReturn(Optional.of(userDto));
        when(equipmentRepository.findById(anyLong())).thenReturn(
                Optional.of(
                        new EquipmentModel(1L, null, null, null, null, 1L, null,
                                List.of(new BackpackItemModel(1L, 1L)))));
        when(itemService.getItem(anyLong())).thenReturn(Optional.of(new ItemDto(ItemType.WEAPON)));
        doAnswer(invocation -> invocation.getArgument(0)).when(equipmentRepository).save(any(EquipmentModel.class));
        when(backpackConfig.getSize()).thenReturn(1);
        //when
        EquipmentModel equipmentModel = underTest.setWeapon(anyString(), 1L);
        //then
        assertThat(equipmentModel.weapon()).isEqualTo(1L);
        assertThat(equipmentModel.backpack().size()).isEqualTo(1);
    }

    @Test
    void shouldThrowItemTypeNotMatchWhileSettingWeapon() {
        //given
        when(userService.getUser(anyString())).thenReturn(Optional.of(userDto));
        when(equipmentRepository.findById(anyLong())).thenReturn(
                Optional.of(
                        new EquipmentModel(1L, null, null, null, null, null, null,
                                List.of(new BackpackItemModel(1L, 1L)))));
        when(itemService.getItem(anyLong())).thenReturn(Optional.of(new ItemDto(ItemType.USE)));
        //when
        Executable setExecutable = () -> underTest.setWeapon(anyString(), 1L);
        //then
        assertThrows(ItemTypeNotMatchException.class, setExecutable);
    }

    @Test
    void shouldThrowItemNotFoundWhileSettingWeapon() {
        //given
        when(userService.getUser(anyString())).thenReturn(Optional.of(userDto));
        when(equipmentRepository.findById(anyLong())).thenReturn(
                Optional.of(
                        new EquipmentModel(1L, null, null, null, null, null, null,
                                List.of(new BackpackItemModel(1L, 1L)))));
        when(itemService.getItem(anyLong())).thenReturn(Optional.empty());
        //when
        Executable setExecutable = () -> underTest.setWeapon(anyString(), 1L);
        //then
        assertThrows(ItemNotFoundException.class, setExecutable);
    }

    @Test
    void shouldThrowUserNotFoundWhileSettingWeapon() {
        //given
        when(userService.getUser(anyString())).thenReturn(Optional.empty());
        //when
        Executable setExecutable = () -> underTest.setWeapon(anyString(), 1L);
        //then
        assertThrows(UserNotFoundException.class, setExecutable);
    }

    @Test
    void shouldThrowItemNotInBackpackWhileSettingWeapon() {
        //given
        when(userService.getUser(anyString())).thenReturn(Optional.of(userDto));
        when(equipmentRepository.findById(anyLong())).thenReturn(
                Optional.of(
                        new EquipmentModel(1L, null, null, null, null, null, null,
                                new ArrayList<>())));
        when(itemService.getItem(anyLong())).thenReturn(Optional.of(new ItemDto(ItemType.WEAPON)));
        //when
        Executable setExecutable = () -> underTest.setWeapon(anyString(), 1L);
        //then
        assertThrows(ItemNotInBackpackException.class, setExecutable);
    }

    @Test
    void shouldReturnEquipmentNotFoundExceptionWhileSettingWeapon() {
        //given
        when(userService.getUser(anyString())).thenReturn(Optional.of(userDto));
        when(equipmentRepository.findById(anyLong())).thenReturn(Optional.empty());
        //when
        Executable setExecutable = () -> underTest.setWeapon(anyString(), 1L);
        //then
        assertThrows(EquipmentNotFoundException.class, setExecutable);
    }

    @Test
    void shouldSetShieldWithoutShieldBefore() {
        //given
        when(userService.getUser(anyString())).thenReturn(Optional.of(userDto));
        when(equipmentRepository.findById(anyLong())).thenReturn(
                Optional.of(
                        new EquipmentModel(1L, null, null, null, null, null, null,
                                List.of(new BackpackItemModel(1L, 1L)))));
        when(itemService.getItem(anyLong())).thenReturn(Optional.of(new ItemDto(ItemType.SHIELD)));
        doAnswer(invocation -> invocation.getArgument(0)).when(equipmentRepository).save(any(EquipmentModel.class));
        //when
        EquipmentModel equipmentModel = underTest.setShield(anyString(), 1L);
        //then
        assertThat(equipmentModel.shield()).isEqualTo(1L);
        assertThat(equipmentModel.backpack().size()).isEqualTo(0);
    }

    @Test
    void shouldSetShieldWithShieldBefore() {
        //given
        when(userService.getUser(anyString())).thenReturn(Optional.of(userDto));
        when(equipmentRepository.findById(anyLong())).thenReturn(
                Optional.of(
                        new EquipmentModel(1L, null, null, null, null, null, 1L,
                                List.of(new BackpackItemModel(1L, 1L)))));
        when(itemService.getItem(anyLong())).thenReturn(Optional.of(new ItemDto(ItemType.SHIELD)));
        doAnswer(invocation -> invocation.getArgument(0)).when(equipmentRepository).save(any(EquipmentModel.class));
        when(backpackConfig.getSize()).thenReturn(1);
        //when
        EquipmentModel equipmentModel = underTest.setShield(anyString(), 1L);
        //then
        assertThat(equipmentModel.shield()).isEqualTo(1L);
        assertThat(equipmentModel.backpack().size()).isEqualTo(1);
    }

    @Test
    void shouldThrowItemTypeNotMatchWhileSettingShield() {
        //given
        when(userService.getUser(anyString())).thenReturn(Optional.of(userDto));
        when(equipmentRepository.findById(anyLong())).thenReturn(
                Optional.of(
                        new EquipmentModel(1L, null, null, null, null, null, null,
                                List.of(new BackpackItemModel(1L, 1L)))));
        when(itemService.getItem(anyLong())).thenReturn(Optional.of(new ItemDto(ItemType.USE)));
        //when
        Executable setExecutable = () -> underTest.setShield(anyString(), 1L);
        //then
        assertThrows(ItemTypeNotMatchException.class, setExecutable);
    }

    @Test
    void shouldThrowItemNotFoundWhileSettingShield() {
        //given
        when(userService.getUser(anyString())).thenReturn(Optional.of(userDto));
        when(equipmentRepository.findById(anyLong())).thenReturn(
                Optional.of(
                        new EquipmentModel(1L, null, null, null, null, null, null,
                                List.of(new BackpackItemModel(1L, 1L)))));
        when(itemService.getItem(anyLong())).thenReturn(Optional.empty());
        //when
        Executable setExecutable = () -> underTest.setShield(anyString(), 1L);
        //then
        assertThrows(ItemNotFoundException.class, setExecutable);
    }

    @Test
    void shouldThrowUserNotFoundWhileSettingShield() {
        //given
        when(userService.getUser(anyString())).thenReturn(Optional.empty());
        //when
        Executable setExecutable = () -> underTest.setShield(anyString(), 1L);
        //then
        assertThrows(UserNotFoundException.class, setExecutable);
    }

    @Test
    void shouldThrowItemNotInBackpackWhileSettingShield() {
        //given
        when(userService.getUser(anyString())).thenReturn(Optional.of(userDto));
        when(equipmentRepository.findById(anyLong())).thenReturn(
                Optional.of(
                        new EquipmentModel(1L, null, null, null, null, null, null,
                                new ArrayList<>())));
        when(itemService.getItem(anyLong())).thenReturn(Optional.of(new ItemDto(ItemType.SHIELD)));
        //when
        Executable setExecutable = () -> underTest.setShield(anyString(), 1L);
        //then
        assertThrows(ItemNotInBackpackException.class, setExecutable);
    }

    @Test
    void shouldReturnEquipmentNotFoundExceptionWhileSettingShield() {
        //given
        when(userService.getUser(anyString())).thenReturn(Optional.of(userDto));
        when(equipmentRepository.findById(anyLong())).thenReturn(Optional.empty());
        //when
        Executable setExecutable = () -> underTest.setShield(anyString(), 1L);
        //then
        assertThrows(EquipmentNotFoundException.class, setExecutable);
    }

    @Test
    void shouldAddItemToBackpack() {
        //given
        when(itemService.isItem(anyLong())).thenReturn(true);
        when(userService.getUser(anyString())).thenReturn(Optional.of(userDto));
        when(equipmentRepository.findById(anyLong())).thenReturn(
                Optional.of(
                        new EquipmentModel(1L, null, null, null, null, null, null,
                                new ArrayList<>())));
        when(backpackConfig.getSize()).thenReturn(1);
        doAnswer(invocation -> invocation.getArgument(0)).when(equipmentRepository).save(any(EquipmentModel.class));
        //when
        EquipmentModel equipmentModel = underTest.addItemToBackpack(anyString(), 1L);
        //then
        assertThat(equipmentModel.backpack().size()).isEqualTo(1);
        assertThat(equipmentModel.backpack().get(0).itemId()).isEqualTo(1L);
    }

    @Test
    void shouldThrowItemNotFoundExceptionWhileAddingItemToBackpack() {
        //given
        when(itemService.isItem(anyLong())).thenReturn(false);
        //when
        Executable addExecutable = () -> underTest.addItemToBackpack(anyString(), 1L);
        //then
        assertThrows(ItemNotFoundException.class, addExecutable);
    }

    @Test
    void shouldThrowUserNotFoundExceptionWhileAddingItemToBackpack() {
        //given
        when(itemService.isItem(anyLong())).thenReturn(true);
        when(userService.getUser(anyString())).thenReturn(Optional.empty());
        //when
        Executable addExecutable = () -> underTest.addItemToBackpack(anyString(), 1L);
        //then
        assertThrows(UserNotFoundException.class, addExecutable);
    }

    @Test
    void shouldThrowEquipmentNotFoundExceptionWhileAddingItemToBackpack() {
        //given
        when(itemService.isItem(anyLong())).thenReturn(true);
        when(userService.getUser(anyString())).thenReturn(Optional.of(userDto));
        when(equipmentRepository.findById(anyLong())).thenReturn(Optional.empty());
        //when
        Executable addExecutable = () -> underTest.addItemToBackpack(anyString(), 1L);
        //then
        assertThrows(EquipmentNotFoundException.class, addExecutable);
    }

    @Test
    void shouldReturnBackpackIsFullExceptionWhileAddingItemToBackpack() {
        //given
        when(itemService.isItem(anyLong())).thenReturn(true);
        when(userService.getUser(anyString())).thenReturn(Optional.of(userDto));
        when(equipmentRepository.findById(anyLong())).thenReturn(
                Optional.of(
                        new EquipmentModel(1L, null, null, null, null, null, null,
                                new ArrayList<>())));
        when(backpackConfig.getSize()).thenReturn(0);
        //when
        Executable addExecutable = () -> underTest.addItemToBackpack(anyString(), 1L);
        //then
        assertThrows(BackpackIsFullException.class, addExecutable);
    }

    @Test
    void shouldRemoveItemFromBackpack() {
        //given
        when(itemService.isItem(anyLong())).thenReturn(true);
        when(userService.getUser(anyString())).thenReturn(Optional.of(userDto));
        when(equipmentRepository.findById(anyLong())).thenReturn(
                Optional.of(
                        new EquipmentModel(1L, null, null, null, null, null, null,
                                List.of(new BackpackItemModel[]{new BackpackItemModel(
                                        1L,
                                        1L
                                )}))));
        doAnswer(invocation -> invocation.getArgument(0)).when(equipmentRepository).save(any(EquipmentModel.class));
        //when
        EquipmentModel equipmentModel = underTest.removeItemFromBackpack(anyString(), 1L);
        //then
        assertThat(equipmentModel.backpack().size()).isEqualTo(0);
    }

    @Test
    void shouldThrowItemNotFoundExceptionWhileRemovingItemFromBackpack() {
        //given
        when(itemService.isItem(anyLong())).thenReturn(false);
        //when
        Executable removeExecutable = () -> underTest.removeItemFromBackpack(anyString(), 1L);
        //then
        assertThrows(ItemNotFoundException.class, removeExecutable);
    }

    @Test
    void shouldThrowUserNotFoundExceptionWhileRemovingItemFromBackpack() {
        //given
        when(itemService.isItem(anyLong())).thenReturn(true);
        when(userService.getUser(anyString())).thenReturn(Optional.empty());
        //when
        Executable removeExecutable = () -> underTest.removeItemFromBackpack(anyString(), 1L);
        //then
        assertThrows(UserNotFoundException.class, removeExecutable);
    }

    @Test
    void shouldThrowEquipmentNotFoundExceptionWhileRemovingItemFromBackpack() {
        //given
        when(itemService.isItem(anyLong())).thenReturn(true);
        when(userService.getUser(anyString())).thenReturn(Optional.of(userDto));
        when(equipmentRepository.findById(anyLong())).thenReturn(Optional.empty());
        //when
        Executable removeExecutable = () -> underTest.removeItemFromBackpack(anyString(), 1L);
        //then
        assertThrows(EquipmentNotFoundException.class, removeExecutable);
    }

    @Test
    void shouldThrowItemNotInBackpackExceptionWhileRemovingItemFromBackpack() {
        //given
        when(itemService.isItem(anyLong())).thenReturn(true);
        when(userService.getUser(anyString())).thenReturn(Optional.of(userDto));
        when(equipmentRepository.findById(anyLong())).thenReturn(
                Optional.of(
                        new EquipmentModel(1L, null, null, null, null, null, null,
                                new ArrayList<>())));
        //when
        Executable removeExecutable = () -> underTest.removeItemFromBackpack(anyString(), 1L);
        //then
        assertThrows(ItemNotInBackpackException.class, removeExecutable);
    }
}