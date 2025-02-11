package com.example.demoapplicant.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

import lombok.Data;

@Data
@Entity
@Table(name = "APPLICANT")
public class ApplicantEntity {
  @Id
  @GeneratedValue
  private Long id;
  private String name;
  private String lastname;
  private LocalDate birthdate;
}
