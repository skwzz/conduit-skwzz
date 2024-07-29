package com.skwzz.domain.user.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO {

    private User user;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class User {
        private String email;
        private String token;
        private String username;
        private String bio;
        private String image;
    }
}