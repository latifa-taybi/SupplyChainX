package com.example.supplyChainXProject.service.user;

import com.example.supplyChainXProject.dto.user.UserDto;
import com.example.supplyChainXProject.entity.User;
import com.example.supplyChainXProject.enums.Role;
import com.example.supplyChainXProject.mapper.user.IUserMapper;
import com.example.supplyChainXProject.repository.user.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService implements IUserService{
    private final IUserRepository userRepository;
    private final IUserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User createUser(UserDto userDto, Role role) {
        Optional<User> existing = userRepository.findByEmail(userDto.email());
        if (existing.isPresent()) {
            return existing.get();
        }
        User user = userMapper.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(userDto.password()));
        user.setRole(role);
        return userRepository.save(user);
    }

    @Override
    public User updateRole(Long id, Role role) {
        User user = userRepository.findById(id).orElseThrow(()->new RuntimeException("user not found"));
        user.setRole(role);
        return userRepository.save(user);
    }

    @Override
    public User searchUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("user not found"));
        if(user == null){
            throw new RuntimeException("User Not Found");
        }
        return user;
    }


}
