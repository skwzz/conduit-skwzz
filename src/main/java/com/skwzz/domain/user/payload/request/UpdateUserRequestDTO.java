package com.skwzz.domain.user.payload.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateUserRequestDTO {

    private User user;

    @Getter
    @Setter
    @ToString
    public static class User {
        private String email;
        private String username;
        private String password;
        private String image;
        private String bio;
        private String encryptedPassword;
    }
}
