package com.fingeinuis.dto;

import lombok.Data;

@Data
public class transactionResponse {

  

    private Long id;
    private Long userId;
    private Double amount;
    private String type;
    private String category;
    private String description;
    private String timestamp;

 
    public static class Builder {

        private Long id;
        private Long userId;
        private Double amount;
        private String type;
        private String category;
        private String description;
        private String timestamp;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder amount(Double amount) {
            this.amount = amount;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder category(String category) {
            this.category = category;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder timestamp(String timestamp) {
            this.timestamp = timestamp;
            return this;
        }


        public transactionResponse build() {
            transactionResponse resp = new transactionResponse();
            resp.id = this.id;
            resp.userId = this.userId;
            resp.amount = this.amount;
            resp.type = this.type;
            resp.category = this.category;
            resp.description = this.description;
            resp.timestamp = this.timestamp;
            return resp;
        }
    }


    public static Builder builder() {
        return new Builder();
    }
}
