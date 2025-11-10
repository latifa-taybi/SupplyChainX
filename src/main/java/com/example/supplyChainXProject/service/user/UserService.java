package com.example.supplyChainXProject.service.user;

import com.example.supplyChainXProject.dto.user.UserDto;
import com.example.supplyChainXProject.entity.User;
import com.example.supplyChainXProject.enums.Role;
import com.example.supplyChainXProject.mapper.user.IUserMapper;
import com.example.supplyChainXProject.repository.user.IUserRepository;
import lombok.AllArgsConstructor;
import org.mapstruct.control.MappingControl;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

@AllArgsConstructor
@Service
public class UserService implements IUserService{
    private final IUserRepository userRepository;
    private final IUserMapper userMapper;

    @Override
    public User createUser(UserDto userDto, Role role) {
        User user = userMapper.toEntity(userDto);
        user.setRole(role);
        return userRepository.save(user);
    }

    @Override
    public User updateRole(Long id, Role role) {
        User user = userRepository.findById(id).orElseThrow(()->new RuntimeException("user not found"));
        user.setRole(role);
        return userRepository.save(user);
    }
}
