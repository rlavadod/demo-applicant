package com.example.demoapplicant.model.api;

import lombok.Data;

@Data
public class RegisterRequest {
  private String name;
  private String email;
  private String password;
}
