package com.lastcivilization.equipmentwriteservice.infrastructure.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients("com.lastcivilization.equipmentwriteservice.infrastructure.service")
public class EquipmentWriteServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EquipmentWriteServiceApplication.class, args);
    }

}
