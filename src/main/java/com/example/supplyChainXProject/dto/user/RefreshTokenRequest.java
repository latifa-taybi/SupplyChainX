package com.example.supplyChainXProject.dto.user;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RefreshTokenRequest {
    private String refreshToken;
}