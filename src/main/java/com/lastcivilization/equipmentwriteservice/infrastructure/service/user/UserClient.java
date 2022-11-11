package com.lastcivilization.equipmentwriteservice.infrastructure.service.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "user-read-service", url = "${test.url:#{null}}")
interface UserClient {

    @GetMapping("/users/{keycloakId}")
    User getUser(@PathVariable String keycloakId);
}
