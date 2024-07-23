package com.skwzz.domain.user.controller.payload.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RegisterRequestDto {

    private User user;

    @Getter
    @Setter
    @ToString
    public static class User {
        private String username;
        private String email;
        private String password;
    }
}
