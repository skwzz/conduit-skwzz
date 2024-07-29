package com.skwzz.domain.user.controller;

import com.skwzz.domain.user.payload.request.LoginRequestDTO;
import com.skwzz.domain.user.payload.request.RegisterRequestDTO;
import com.skwzz.domain.user.payload.response.UserResponseDTO;
import com.skwzz.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<UserResponseDTO> register(@RequestBody RegisterRequestDTO request){
        return ResponseEntity.ok().body(userService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody LoginRequestDTO request){
        return ResponseEntity.ok(userService.login(request));
    }
}
