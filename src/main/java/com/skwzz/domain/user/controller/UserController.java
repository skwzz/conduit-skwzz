package com.skwzz.domain.user.controller;

import com.skwzz.domain.user.controller.auth.AuthenticatedUser;
import com.skwzz.domain.user.payload.request.UpdateUserRequestDTO;
import com.skwzz.domain.user.payload.response.UserResponseDTO;
import com.skwzz.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserResponseDTO> getCurrentUserInfo(@AuthenticationPrincipal AuthenticatedUser authenticatedUser){
        return ResponseEntity.ok().body(userService.getUserInfo(authenticatedUser.getUser()));
    }

    @PutMapping
    public ResponseEntity<UserResponseDTO> updateUserInfo(@RequestBody UpdateUserRequestDTO request,
                                                          @AuthenticationPrincipal AuthenticatedUser authenticatedUser){
        return ResponseEntity.ok().body(userService.updateUserInfo(request, authenticatedUser.getUser()));
    }
}
