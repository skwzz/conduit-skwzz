package com.skwzz.domain.user.mapper;


import com.skwzz.domain.user.entity.User;
import com.skwzz.domain.user.payload.response.UserResponseDTO;

public class UserMapper {

    public static UserResponseDTO toDto(User user, String... token){
        UserResponseDTO.User inObj = UserResponseDTO.User.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .image(user.getImage())
                .bio(user.getBio())
                .build();
        if(token.length > 0){
            inObj.setToken(token[0]);
        }
        return UserResponseDTO.builder()
                .user(inObj)
                .build();
    }
}
