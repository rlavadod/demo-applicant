package com.example.demoapplicant.model.api;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Applicant {
  private Long id;
  private String name;
  private String lastname;
  private Integer age;
  private LocalDate birthdate;
  private String lifeExpectancy;
}
