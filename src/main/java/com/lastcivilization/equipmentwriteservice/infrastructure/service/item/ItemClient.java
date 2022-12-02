package com.lastcivilization.equipmentwriteservice.infrastructure.service.item;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "item-read-service", url = "${test.url:#{null}}")
interface ItemClient {

    @GetMapping("/items/{id}")
    Item getItem(@PathVariable long id);

    @GetMapping("/items/{id}/exists")
    boolean isItem(@PathVariable long id);
}
