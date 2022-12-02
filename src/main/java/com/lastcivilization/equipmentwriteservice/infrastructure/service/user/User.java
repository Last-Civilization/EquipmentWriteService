package com.lastcivilization.equipmentwriteservice.infrastructure.service.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
record User(
        @JsonProperty("equipment")
        long equipment
) { }
