package com.example.supplyChainXProject.mapper.user;

import com.example.supplyChainXProject.dto.user.UserDto;
import com.example.supplyChainXProject.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUserMapper {
    User toEntity(UserDto userDto);
    UserDto toDto(User user);
}
