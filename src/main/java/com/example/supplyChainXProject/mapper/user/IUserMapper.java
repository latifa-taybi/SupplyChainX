package com.example.supplyChainXProject.mapper.user;

import com.example.supplyChainXProject.dto.user.UserDto;
import com.example.supplyChainXProject.entity.UserApp;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUserMapper {
    UserApp toEntity(UserDto userDto);
    UserDto toDto(UserApp userApp);
}
