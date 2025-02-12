package com.example.demoapplicant.model.api;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Applicant {
  private Long id;
  @NotNull
  @Pattern(regexp = "^[a-zA-Z]+")
  @Size(min = 1, max = 100)
  private String name;
  @NotNull
  @Pattern(regexp = "^[a-zA-Z]+")
  @Size(min = 1, max = 100)
  private String lastname;
  private Integer age;
  @NotNull
  private LocalDate birthdate;
  private String lifeExpectancy;
}
