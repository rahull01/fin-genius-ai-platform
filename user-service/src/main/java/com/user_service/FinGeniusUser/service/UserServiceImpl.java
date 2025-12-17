package com.user_service.FinGeniusUser.service;

import com.user_service.FinGeniusUser.dto.UserLoginRequest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.user_service.FinGeniusUser.dto.UserRegistrationRequest;
import com.user_service.FinGeniusUser.dto.UserResponse;
import com.user_service.FinGeniusUser.entity.User;
import com.user_service.FinGeniusUser.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService
{

    private final UserRepository userRepository;
    @Override
    public UserResponse registerUser(UserRegistrationRequest request) {
        if(userRepository.existsByEmail(request.getEmail()))
        {
            throw new IllegalArgumentException("Email Already Registered : ");
        }
        String hashed = BCrypt.hashpw(request.getPassword(),BCrypt.gensalt());

        User user =  User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .passwordHash(hashed)
                .role("USER")
                .country(request.getCountry())
                .currency(request.getCurrency() != null ? request.getCurrency() : "INR").build();

        User saved = userRepository.save(user);

        return UserResponse.builder()
                .id(saved.getId())
                .fullName(saved.getFullName())
                .email(saved.getEmail())
                .role(saved.getRole())
                .country(saved.getCountry())
                .currency(saved.getCurrency())
                .build();
    }

    @Override
    public UserResponse getUserById(Long id)
    {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("User Not Found"));

        return UserResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole())
                .country(user.getCountry())
                .currency(user.getCurrency())
                .build();
    }


    @Override
    public UserResponse login(UserLoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("User Not Found"));

        if (!BCrypt.checkpw(request.getPassword(), user.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid Password");
        }

        return UserResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole())
                .country(user.getCountry())
                .currency(user.getCurrency())
                .build();
    }
    }

