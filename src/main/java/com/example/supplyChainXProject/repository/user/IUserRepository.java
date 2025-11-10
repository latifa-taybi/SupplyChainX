package com.example.supplyChainXProject.repository.user;

import com.example.supplyChainXProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
}
