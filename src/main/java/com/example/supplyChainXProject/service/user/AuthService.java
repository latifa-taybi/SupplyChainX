package com.example.supplyChainXProject.service.user;

import com.example.supplyChainXProject.dto.user.AuthResponse;
import com.example.supplyChainXProject.dto.user.LoginRequest;
import com.example.supplyChainXProject.dto.user.RefreshTokenRequest;
import com.example.supplyChainXProject.entity.RefreshToken;
import com.example.supplyChainXProject.entity.User;
import com.example.supplyChainXProject.repository.user.IUserRepository;
import com.example.supplyChainXProject.security.jwt.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final IUserRepository userRepository;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(()->new RuntimeException("not found"));

        String accessToken = jwtService.generateToken(user);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);

        return new AuthResponse(accessToken, refreshToken.getToken());
    }

    public AuthResponse refreshToken(RefreshTokenRequest request) {
        RefreshToken refreshToken = refreshTokenService.verifyRefreshToken(request.getRefreshToken());

        String accessToken = jwtService.generateToken(refreshToken.getUser());

        RefreshToken newRefreshToken = refreshTokenService.rotateRefreshToken(refreshToken);

        return new AuthResponse(accessToken, newRefreshToken.getToken());
    }

    public void logout(RefreshTokenRequest request) {
        refreshTokenService.revokeToken(request.getRefreshToken());
    }
}
