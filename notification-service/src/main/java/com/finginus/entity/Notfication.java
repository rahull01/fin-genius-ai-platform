package com.finginus.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notfication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id") 
    private long userId;
    private String message;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void pre() {
        this.createdAt = LocalDateTime.now();
    }



    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Long id;
        private long userId;
        private String message;
        private LocalDateTime createdAt;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder userId(long userId) {
            this.userId = userId;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Notfication build() {
            Notfication n = new Notfication();
            n.setId(this.id);
            n.setUserId(this.userId);
            n.setMessage(this.message);
            n.setCreatedAt(this.createdAt);
            return n;
        }
    }

  
}
