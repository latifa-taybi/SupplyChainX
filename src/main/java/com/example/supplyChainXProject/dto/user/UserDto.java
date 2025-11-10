package com.example.supplyChainXProject.dto.user;

import com.example.supplyChainXProject.enums.Role;
import jakarta.validation.constraints.NotEmpty;

public record UserDto (
        @NotEmpty(message = "first name is required")
        String firstName,

        @NotEmpty(message = "last name is required")
        String lastName,

        @NotEmpty(message = "email is required")
        String email,

        @NotEmpty(message = "password is required")
        String password
){}
