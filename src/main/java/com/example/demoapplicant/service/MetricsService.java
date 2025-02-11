package com.example.demoapplicant.service;

import com.example.demoapplicant.model.api.Metrics;

public interface MetricsService {
  Metrics obtainAllAverageAge();
  Metrics obtainAllStandardDeviation();
}
