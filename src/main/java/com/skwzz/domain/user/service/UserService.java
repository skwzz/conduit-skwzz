package com.skwzz.domain.user.service;

import com.skwzz.domain.user.payload.request.LoginRequestDTO;
import com.skwzz.domain.user.payload.request.RegisterRequestDTO;
import com.skwzz.domain.user.entity.User;
import com.skwzz.domain.user.payload.response.UserResponseDTO;
import com.skwzz.domain.user.repository.UserRepository;
import com.skwzz.global.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Transactional
    public UserResponseDTO register(RegisterRequestDTO request) {
        log.info(request.toString());
        RegisterRequestDTO.User requestUser = request.getUser();
        User user = User.builder()
                .email(requestUser.getEmail())
                .username(requestUser.getUsername())
                .password(passwordEncoder.encode(requestUser.getEmail()))
                .build();
        userRepository.save(user);

        return UserResponseDTO.builder()
                .user(UserResponseDTO.User.builder()
                        .email(user.getEmail())
                        .username(user.getUsername())
                        .build())
                .build();
    }

    public UserResponseDTO login(LoginRequestDTO request) {
        try{
            log.info(request.toString());
            LoginRequestDTO.User user = request.getUser();
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            User findUser = userRepository.findByEmail(user.getEmail());
            String token = jwtUtil.createToken(user.getEmail());

            return UserResponseDTO.builder()
                    .user(UserResponseDTO.User.builder()
                            .email(findUser.getUsername())
                            .token(token)
                            .username(findUser.getUsername())
                            .bio(findUser.getBio())
                            .image(findUser.getImage())
                            .build())
                    .build();
        }catch (Exception e){
            log.error(e.getMessage());
            throw new RuntimeException("Invalid login credentials", e);
        }
    }
}
