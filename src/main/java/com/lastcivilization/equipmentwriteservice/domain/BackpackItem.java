package com.lastcivilization.equipmentwriteservice.domain;

class BackpackItem {

    private Long id;
    private Long itemId;
    private int amount;

    public BackpackItem(Long id, Long itemId, int amount) {
        this.id = id;
        this.itemId = itemId;
        this.amount = amount;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public static final class Builder {

        private Long id;
        private Long itemId;
        private int amount;

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

        public Builder amount(int amount) {
            this.amount = amount;
            return this;
        }

        public BackpackItem build() {
            return new BackpackItem(id, itemId, amount);
        }
    }
}
