package com.skwzz.domain.user.repository;

import com.skwzz.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
    List<User> findByUsernameOrEmailAndIdxNot(String username, String email, Long idx);
}
