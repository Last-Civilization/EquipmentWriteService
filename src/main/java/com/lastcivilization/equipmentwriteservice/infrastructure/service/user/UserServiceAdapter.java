package com.lastcivilization.equipmentwriteservice.infrastructure.service.user;

import com.lastcivilization.equipmentwriteservice.domain.exception.ApplicationException;
import com.lastcivilization.equipmentwriteservice.domain.port.UserService;
import com.lastcivilization.equipmentwriteservice.domain.port.dto.UserDto;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.lastcivilization.equipmentwriteservice.infrastructure.service.user.UserMapper.MAPPER;

@Service
@RequiredArgsConstructor
class UserServiceAdapter implements UserService {

    private final UserClient userClient;

    @Override
    public Optional<UserDto> getUser(String keycloakId) {
        Optional<UserDto> userDtoOptional;
        try{
            User user = userClient.getUser(keycloakId);
            userDtoOptional = Optional.of(user)
                    .map(MAPPER::toDto);
        } catch (FeignException exception){
            if(exception.status() == 404){
                userDtoOptional = Optional.empty();
            } else {
                throw new ApplicationException(exception);
            }
        }
        return userDtoOptional;
    }
}
