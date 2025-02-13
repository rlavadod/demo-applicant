package com.example.demoapplicant.service;

import com.example.demoapplicant.model.entity.User;

public interface JwtService {
  String extractUsername(String token);
  String generateToken(User user);
  String generateRefreshToken(User user);
  boolean isTokenValid(String token, User user);
}
