package com.lastcivilization.equipmentwriteservice.domain;

class BackpackItem {

    private Long id;
    private Long itemId;

    public BackpackItem(Long id, Long itemId) {
        this.id = id;
        this.itemId = itemId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public static final class Builder {

        private Long id;
        private Long itemId;

        private Builder() {
        }

        public static Builder aBackpackItem() {
            return new Builder();
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder itemId(Long itemId) {
            this.itemId = itemId;
            return this;
        }

        public BackpackItem build() {
            return new BackpackItem(id, itemId);
        }
    }
}
