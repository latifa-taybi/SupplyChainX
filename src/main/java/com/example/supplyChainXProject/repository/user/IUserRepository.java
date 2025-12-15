package com.example.supplyChainXProject.repository.user;

import com.example.supplyChainXProject.entity.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserApp, Long> {
    UserApp findUserByEmail (String email);
}
