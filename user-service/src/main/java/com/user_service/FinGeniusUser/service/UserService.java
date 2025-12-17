package com.user_service.FinGeniusUser.service;

import com.user_service.FinGeniusUser.dto.UserLoginRequest;
import com.user_service.FinGeniusUser.dto.UserRegistrationRequest;
import com.user_service.FinGeniusUser.dto.UserResponse;

public interface UserService
{
    UserResponse registerUser(UserRegistrationRequest request);
    UserResponse login(UserLoginRequest request);
    UserResponse getUserById(Long id);
}
