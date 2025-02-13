package com.example.demoapplicant.service;

import com.example.demoapplicant.model.api.AuthRequest;
import com.example.demoapplicant.model.api.RegisterRequest;
import com.example.demoapplicant.model.api.TokenResponse;

public interface AuthService {
  TokenResponse register(RegisterRequest request);
  TokenResponse authenticate(AuthRequest request);
  TokenResponse refreshToken(String authentication);
}
