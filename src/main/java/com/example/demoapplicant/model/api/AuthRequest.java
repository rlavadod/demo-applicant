package com.example.demoapplicant.model.api;

import lombok.Data;

@Data
public class AuthRequest {
  private String email;
  private String password;
}
