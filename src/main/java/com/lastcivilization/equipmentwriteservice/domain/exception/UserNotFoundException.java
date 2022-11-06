package com.lastcivilization.equipmentwriteservice.domain.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String keycloakId) {
        super("Can not found user with keycloak id: %s".formatted(keycloakId));
    }
}
