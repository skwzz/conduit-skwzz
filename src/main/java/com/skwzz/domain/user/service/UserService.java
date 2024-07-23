package com.skwzz.domain.user.service;

import com.skwzz.domain.user.controller.payload.request.RegisterRequestDto;
import com.skwzz.domain.user.entity.User;
import com.skwzz.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Boolean register(RegisterRequestDto request) {
        log.info(request.toString());
        RegisterRequestDto.User requestUser = request.getUser();
        User user = User.builder()
                .email(requestUser.getEmail())
                .username(requestUser.getUsername())
                .password(requestUser.getEmail())
                .build();
        userRepository.save(user);
        return true;
    }
}
