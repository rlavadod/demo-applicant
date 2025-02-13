package com.example.demoapplicant.controller;

import com.example.demoapplicant.model.api.AuthRequest;
import com.example.demoapplicant.model.api.RegisterRequest;
import com.example.demoapplicant.model.api.TokenResponse;
import com.example.demoapplicant.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

  @Autowired
  private AuthService service;

  @PostMapping("/register")
  public ResponseEntity<TokenResponse> register(@RequestBody RegisterRequest request) {
    TokenResponse response = service.register(request);
    return ResponseEntity.ok(response);
  }

  @PostMapping("/login")
  public ResponseEntity<TokenResponse> authenticate(@RequestBody AuthRequest request) {
    TokenResponse response = service.authenticate(request);
    return ResponseEntity.ok(response);
  }

  @PostMapping("/refresh-token")
  public TokenResponse refreshToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String authentication) {
    return service.refreshToken(authentication);
  }

}
