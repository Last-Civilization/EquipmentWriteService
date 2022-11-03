package com.lastcivilization.equipmentwriteservice.infrastructure.application.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationPropertiesScan("com.lastcivilization.equipmentwriteservice.infrastructure.backpack")
class BeanConfiguration {

}
