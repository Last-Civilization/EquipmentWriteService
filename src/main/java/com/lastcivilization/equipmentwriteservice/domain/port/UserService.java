package com.lastcivilization.equipmentwriteservice.domain.port;

import com.lastcivilization.equipmentwriteservice.domain.port.dto.UserDto;

public interface UserService {

    UserDto getUser(String keycloakId);
}
