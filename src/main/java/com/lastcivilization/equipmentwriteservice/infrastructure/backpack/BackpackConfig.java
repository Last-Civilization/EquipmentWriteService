package com.lastcivilization.equipmentwriteservice.infrastructure.backpack;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "backpack")
class BackpackConfig {

    private int size;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
