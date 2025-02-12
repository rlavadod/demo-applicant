package com.example.demoapplicant.util;

import com.example.demoapplicant.model.api.Applicant;
import com.example.demoapplicant.model.entity.ApplicantEntity;

import java.time.LocalDate;

public class MockUtil {

  public static Applicant obtainApplicant() {
    return Applicant.builder()
        .id(1L)
        .name("")
        .lastname("")
        .birthdate(LocalDate.of(1990, 5, 1))
        .build();
  }

  public static ApplicantEntity obtainApplicantEntity() {
    ApplicantEntity entity = new ApplicantEntity();
    entity.setId(1L);
    entity.setName("");
    entity.setLastname("");
    entity.setBirthdate(LocalDate.of(1990, 5, 1));

    return entity;
  }
}
