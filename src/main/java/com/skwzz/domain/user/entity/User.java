package com.skwzz.domain.user.entity;


import com.skwzz.domain.user.payload.request.UpdateUserRequestDTO;
import jakarta.persistence.*;
import lombok.*;

@ToString
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "member")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column
    private String bio;

    @Column
    private String image;

    public User changeUserInfo(UpdateUserRequestDTO.User dto){
        if(dto.getUsername() != null && !dto.getUsername().isBlank()) changeUsername(dto.getUsername());
        if(dto.getEmail() != null && !dto.getEmail().isBlank()) changeEmail(dto.getEmail());
        if(dto.getPassword() != null && !dto.getPassword().isBlank()) changePassword(dto.getEncryptedPassword());
        changeBio(dto.getBio());
        changeImage(dto.getImage());
        return this;
    }

    public void changeUsername(String username){
        this.username = username;
    }

    public void changeEmail(String email){
        this.email = email;
    }

    public void changePassword(String encryptedPassword){
        this.password = encryptedPassword;
    }

    public void changeBio(String bio){
        this.bio = bio;
    }

    public void changeImage(String image){

    }
}
