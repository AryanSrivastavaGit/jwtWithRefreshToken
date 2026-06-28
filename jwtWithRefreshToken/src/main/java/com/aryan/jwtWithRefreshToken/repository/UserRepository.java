package com.aryan.jwtWithRefreshToken.repository;

import com.aryan.jwtWithRefreshToken.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
