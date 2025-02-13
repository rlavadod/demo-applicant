package com.example.demoapplicant.service.impl;

import com.example.demoapplicant.model.api.AuthRequest;
import com.example.demoapplicant.model.api.RegisterRequest;
import com.example.demoapplicant.model.api.TokenResponse;
import com.example.demoapplicant.model.entity.Token;
import com.example.demoapplicant.model.entity.User;
import com.example.demoapplicant.repository.TokenRepository;
import com.example.demoapplicant.repository.UserRepository;
import com.example.demoapplicant.service.AuthService;
import com.example.demoapplicant.service.JwtService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
  @Autowired
  private UserRepository repository;
  @Autowired
  private TokenRepository tokenRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private JwtService jwtService;
  @Autowired
  private AuthenticationManager authenticationManager;

  @Override
  public TokenResponse register(RegisterRequest request) {
    User user = User.builder()
        .name(request.getName())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .build();

    User savedUser = repository.save(user);
    String jwtToken = jwtService.generateToken(savedUser);
    String refreshToken = jwtService.generateRefreshToken(savedUser);

    saveUserToken(savedUser, jwtToken);
    return new TokenResponse(jwtToken, refreshToken);
  }

  @Override
  public TokenResponse authenticate(AuthRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    final User user = repository.findByEmail(request.getEmail())
        .orElseThrow();
    final String accessToken = jwtService.generateToken(user);
    final String refreshToken = jwtService.generateRefreshToken(user);
    revokeAllUserTokens(user);
    saveUserToken(user, accessToken);
    return new TokenResponse(accessToken, refreshToken);
  }

  private void saveUserToken(User user, String jwtToken) {
    final Token token = Token.builder()
        .user(user)
        .token(jwtToken)
        .tokenType(Token.TokenType.BEARER)
        .isExpired(false)
        .isRevoked(false)
        .build();
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(User user) {
    final List<Token> validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
    if (!validUserTokens.isEmpty()) {
      validUserTokens.forEach(token -> {
        token.setIsExpired(true);
        token.setIsRevoked(true);
      });
      tokenRepository.saveAll(validUserTokens);
    }
  }

  @Override
  public TokenResponse refreshToken(String authentication) {

    if (authentication == null || !authentication.startsWith("Bearer ")) {
      throw new IllegalArgumentException("Invalid auth header");
    }
    final String refreshToken = authentication.substring(7);
    final String userEmail = jwtService.extractUsername(refreshToken);
    if (userEmail == null) {
      return null;
    }

    final User user = this.repository.findByEmail(userEmail).orElseThrow();
    final boolean isTokenValid = jwtService.isTokenValid(refreshToken, user);
    if (!isTokenValid) {
      return null;
    }

    final String accessToken = jwtService.generateRefreshToken(user);
    revokeAllUserTokens(user);
    saveUserToken(user, accessToken);

    return new TokenResponse(accessToken, refreshToken);
  }

}
