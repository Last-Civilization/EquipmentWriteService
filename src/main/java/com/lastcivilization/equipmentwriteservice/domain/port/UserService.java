package com.lastcivilization.equipmentwriteservice.domain.port;

import com.lastcivilization.equipmentwriteservice.domain.port.dto.UserDto;

import java.util.Optional;

public interface UserService {

    Optional<UserDto> getUser(String keycloakId);
}
