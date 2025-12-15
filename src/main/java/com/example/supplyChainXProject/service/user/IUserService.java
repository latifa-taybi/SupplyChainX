package com.example.supplyChainXProject.service.user;

import com.example.supplyChainXProject.dto.user.UserDto;
import com.example.supplyChainXProject.entity.UserApp;
import com.example.supplyChainXProject.enums.Role;

public interface IUserService {
    UserApp createUser(UserDto userDto, Role role);
    UserApp updateRole(Long id, Role role);
    UserApp searchUserByEmail(String email);
}
