package com.fingeinuis.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDto
 {
  
    private Long id;
    private String fullName;
    private String email;
    private String passwordHash;
    private String role;
    private String country;
    private String currency;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
