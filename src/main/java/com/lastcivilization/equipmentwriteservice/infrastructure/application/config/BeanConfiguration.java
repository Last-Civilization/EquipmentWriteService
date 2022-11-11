package com.lastcivilization.equipmentwriteservice.infrastructure.application.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("com.lastcivilization.equipmentwriteservice.infrastructure")
@ConfigurationPropertiesScan("com.lastcivilization.equipmentwriteservice.infrastructure.backpack")
@EntityScan("com.lastcivilization.equipmentwriteservice.infrastructure.database")
@EnableJpaRepositories("com.lastcivilization.equipmentwriteservice.infrastructure.database")
class BeanConfiguration {

}
