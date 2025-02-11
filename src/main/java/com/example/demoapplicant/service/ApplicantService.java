package com.example.demoapplicant.service;

import com.example.demoapplicant.model.api.Applicant;

import java.util.List;

public interface ApplicantService {
  Applicant saveApplicant(Applicant flight);
  List<Applicant> getAll();
}
