package com.example.demoapplicant.controller;

import com.example.demoapplicant.model.api.Metrics;
import com.example.demoapplicant.service.MetricsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/applicant-management/v1/metrics")
@Slf4j
public class MetricsController {

  @Autowired
  private MetricsService service;

  @GetMapping("/age-average")
  public ResponseEntity<Metrics> getAllAgeAverage() {
    return ResponseEntity.ok(service.obtainAllAverageAge());
  }

  @GetMapping("/age-standard-deviation")
  public ResponseEntity<Metrics> getAllAgeStandardDeviation() {
    return ResponseEntity.ok(service.obtainAllStandardDeviation());
  }

}
