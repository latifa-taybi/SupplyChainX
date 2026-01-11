package com.example.supplyChainXProject.repository.user;

import com.example.supplyChainXProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail (String email);
}
