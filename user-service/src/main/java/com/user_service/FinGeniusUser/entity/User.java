package com.user_service.FinGeniusUser.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter @AllArgsConstructor @NoArgsConstructor @Builder
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String fullName;
    @Column(unique = true , nullable = false)
    private String email;
    @Column(nullable = false)
    private String passwordHash;
    @Column(nullable = false)
    private String role;
    private String country;
    private String currency;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist()
    {
       this.createdAt = LocalDateTime.now();
       this.updatedAt = this.createdAt;
    }

    @PreUpdate
    public void preUpdate()
    {
        this.updatedAt = LocalDateTime.now();
    }

}
