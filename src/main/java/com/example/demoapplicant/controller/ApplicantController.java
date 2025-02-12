package com.example.demoapplicant.controller;

import com.example.demoapplicant.model.api.Applicant;
import com.example.demoapplicant.service.ApplicantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

  @Operation(summary = "Create a new applicant")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201",
          description = "Applicant created successfully",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = Applicant.class))}),
      @ApiResponse(responseCode = "400", description = "Invalid input provided")})
  @PostMapping(value = "/")
  public ResponseEntity<?> saveApplicant(@io.swagger.v3.oas.annotations.parameters.RequestBody(
      description = "Applicant to create",
      required = true,
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = Applicant.class),
          examples = @ExampleObject(value = "{\"name\":\"Carlos\",\"lastname\":\"Alcantara\",\"birthdate\":\"1980-02-09\"}")))
                                         @RequestBody Applicant applicant) {
    return new ResponseEntity<>(service.saveApplicant(applicant), HttpStatus.CREATED);
  }

  @Operation(summary = "Obtain all applicants")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Obtain all applicants",
          content = {@Content(mediaType = "application/json",
              array = @ArraySchema(schema = @Schema(implementation = Applicant.class)))}),
      @ApiResponse(responseCode = "400", description = "Not found any applicant",
          content = @Content)})
  @GetMapping("/")
  public ResponseEntity<List<Applicant>> getAllApplicants() {
    return ResponseEntity.ok(service.getAll());
  }
}
