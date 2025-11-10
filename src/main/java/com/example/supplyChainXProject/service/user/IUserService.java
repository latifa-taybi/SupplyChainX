package com.example.supplyChainXProject.service.user;

import com.example.supplyChainXProject.dto.user.UserDto;
import com.example.supplyChainXProject.entity.User;
import com.example.supplyChainXProject.enums.Role;

public interface IUserService {
    User createUser(UserDto userDto, Role role);
    User updateRole(Long id, Role role);
}
