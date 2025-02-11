package com.example.demoapplicant.service.impl;

import com.example.demoapplicant.model.api.Metrics;
import com.example.demoapplicant.model.entity.ApplicantEntity;
import com.example.demoapplicant.repository.ApplicantRepository;
import com.example.demoapplicant.service.MetricsService;
import com.example.demoapplicant.util.Util;
import java.util.List;
import java.util.OptionalDouble;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetricsServiceImpl implements MetricsService {

  @Autowired
  private ApplicantRepository repository;

  @Override
  public Metrics obtainAllAverageAge() {
    OptionalDouble average = repository.findAll().stream()
        .map(ApplicantEntity::getBirthdate)
        .mapToDouble(Util::obtainAge)
        .average();
    if (average.isPresent()) {
      return Metrics.builder()
          .average(average.getAsDouble())
          .build();
    }
    return Metrics.builder()
        .average(Double.NaN)
        .build();
  }

  @Override
  public Metrics obtainAllStandardDeviation() {
    List<Integer> values = repository.findAll().stream()
        .map(ApplicantEntity::getBirthdate)
        .map(Util::obtainAge)
        .toList();
    if (values.size() > 0) {
      return Metrics.builder()
          .deviationStandard(Util.obtainStandardDeviation(values))
          .build();
    }
    return Metrics.builder()
        .deviationStandard(Double.NaN)
        .build();
  }
}
