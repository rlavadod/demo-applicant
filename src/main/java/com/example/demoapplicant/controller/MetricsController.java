package com.example.demoapplicant.controller;

import com.example.demoapplicant.model.api.Metrics;
import com.example.demoapplicant.service.MetricsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

  @Operation(summary = "Obtain all age average")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Obtain all average applicant age",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = Metrics.class))})})
  @GetMapping("/age-average")
  public ResponseEntity<Metrics> getAllAgeAverage() {
    return ResponseEntity.ok(service.obtainAllAverageAge());
  }

  @Operation(summary = "Obtain all age standard deviation")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Obtain all age standard deviation applicants",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = Metrics.class))})})
  @GetMapping("/age-standard-deviation")
  public ResponseEntity<Metrics> getAllAgeStandardDeviation() {
    return ResponseEntity.ok(service.obtainAllStandardDeviation());
  }

}
