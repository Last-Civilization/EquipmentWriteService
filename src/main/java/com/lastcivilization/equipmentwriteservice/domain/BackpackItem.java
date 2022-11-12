package com.lastcivilization.equipmentwriteservice.domain;

class BackpackItem {

    private Long id;
    private long itemId;

    public BackpackItem(Long id, long itemId) {
        this.id = id;
        this.itemId = itemId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public static final class Builder {

        private Long id;
        private long itemId;

        private Builder() {
        }

        public static Builder aBackpackItem() {
            return new Builder();
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder itemId(long itemId) {
            this.itemId = itemId;
            return this;
        }

        public BackpackItem build() {
            return new BackpackItem(id, itemId);
        }
    }
}
