package com.user_service.FinGeniusUser.service;

import com.user_service.FinGeniusUser.dto.UserRegistrationRequest;
import com.user_service.FinGeniusUser.dto.UserResponse;

public interface UserService
{
    UserResponse registerUser(UserRegistrationRequest request);
    UserResponse getUserByEmail(String email);
}
