package com.example.demoapplicant.controller;

import com.example.demoapplicant.model.api.Applicant;
import com.example.demoapplicant.service.ApplicantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/applicant-management/v1/applicant")
@Slf4j
public class ApplicantController {

  @Autowired
  private ApplicantService service;

  @PostMapping(value = "/")
  public ResponseEntity<?> saveApplicant(@RequestBody Applicant applicant) {
    return new ResponseEntity<>(service.saveApplicant(applicant), HttpStatus.CREATED);
  }

  @GetMapping("/")
  public ResponseEntity<List<Applicant>> getAllUsers() {
    return ResponseEntity.ok(service.getAll());
  }

}
