package com.user_service.FinGeniusUser.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user_service.FinGeniusUser.dto.UserRegistrationRequest;
import com.user_service.FinGeniusUser.dto.UserResponse;
import com.user_service.FinGeniusUser.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController
{
    private final UserService userService;
  @PostMapping("/register")
  public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRegistrationRequest request)
  {
      UserResponse userResponse = userService.registerUser(request);
      return ResponseEntity.ok(userResponse);
  }

  /*@PostMapping("/login")
  public ResponseEntity<UserResponse> login(@Valid @RequestBody UserRegistrationRequest request)
  {
      UserResponse userResponse = userService.login(request);
      return ResponseEntity.ok(userResponse);
  }*/

  @GetMapping("/get_user/{email}")
  public ResponseEntity<UserResponse> getByEmail(@PathVariable String email)
  {
      UserResponse userResponse = userService.getUserByEmail(email);
      return ResponseEntity.ok(userResponse);
  }

}
