package com.example.supplyChainXProject.service.user;

import com.example.supplyChainXProject.dto.user.UserDto;
import com.example.supplyChainXProject.entity.UserApp;
import com.example.supplyChainXProject.enums.Role;
import com.example.supplyChainXProject.mapper.user.IUserMapper;
import com.example.supplyChainXProject.repository.user.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService implements IUserService{
    private final IUserRepository userRepository;
    private final IUserMapper userMapper;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserApp createUser(UserDto userDto, Role role) {
        UserApp existing = userRepository.findUserByEmail(userDto.email());
        if (existing != null) {
            return existing;
        }
        UserApp userApp = userMapper.toEntity(userDto);
        String encodedPassword = passwordEncoder.encode(userDto.password());
        userApp.setPassword(encodedPassword);
        userApp.setRole(role);
        return userRepository.save(userApp);
    }

    @Override
    public UserApp updateRole(Long id, Role role) {
        UserApp userApp = userRepository.findById(id).orElseThrow(()->new RuntimeException("user not found"));
        userApp.setRole(role);
        return userRepository.save(userApp);
    }

    @Override
    public UserApp searchUserByEmail(String email) {
        UserApp userApp = userRepository.findUserByEmail(email);
        if(userApp == null){
            throw new RuntimeException("User Not Found");
        }
        return userApp;
    }


}
