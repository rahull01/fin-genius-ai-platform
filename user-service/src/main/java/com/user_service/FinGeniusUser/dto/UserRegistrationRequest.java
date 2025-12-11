package com.user_service.FinGeniusUser.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRegistrationRequest
{
    @NotBlank
    private String fullName;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    private String Country;
    private String currency;
}
