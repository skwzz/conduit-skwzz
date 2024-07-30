package com.skwzz.domain.user.service;

import com.skwzz.domain.user.mapper.UserMapper;
import com.skwzz.domain.user.payload.request.LoginRequestDTO;
import com.skwzz.domain.user.payload.request.RegisterRequestDTO;
import com.skwzz.domain.user.entity.User;
import com.skwzz.domain.user.payload.request.UpdateUserRequestDTO;
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

import java.util.List;

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
                .password(passwordEncoder.encode(requestUser.getPassword()))
                .build();
        userRepository.save(user);

        return UserMapper.toDto(user);
    }

    public UserResponseDTO login(LoginRequestDTO request) {
        try{
            log.info(request.toString());
            LoginRequestDTO.User user = request.getUser();
            User findUser = userRepository.findByEmail(user.getEmail());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(findUser.getUsername(), user.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtUtil.createToken(findUser.getUsername());

            return UserMapper.toDto(findUser, token);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new RuntimeException("Invalid login credentials", e);
        }
    }

    public UserResponseDTO getUserInfo(User user){
        return UserMapper.toDto(user);
    }


    @Transactional
    public UserResponseDTO updateUserInfo(UpdateUserRequestDTO request, User user) {
        log.info(request.toString());
        UpdateUserRequestDTO.User forUpdateDto = request.getUser();
        User findUser = userRepository.findByUsername(user.getUsername());
        List<User> byUsernameOrEmailAndIdxNot = userRepository.findByUsernameOrEmailAndIdxNot(forUpdateDto.getUsername(), forUpdateDto.getEmail(), findUser.getIdx());

        if(!byUsernameOrEmailAndIdxNot.isEmpty()){
            throw new RuntimeException("이미 사용중인 이름이나 이메일이 존재합니다.");
        }

        User changedUser = findUser.changeUserInfo(forUpdateDto);
        return UserMapper.toDto(changedUser);
    }
}
