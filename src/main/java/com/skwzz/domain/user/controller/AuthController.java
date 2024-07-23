package com.skwzz.domain.user.controller;

import com.skwzz.domain.user.controller.payload.request.RegisterRequestDto;
import com.skwzz.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public ResponseEntity<Boolean> register(@RequestBody RegisterRequestDto request){
        return ResponseEntity.ok().body(userService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(){
        return ResponseEntity.ok().body(true);
    }
}
