package com.example.demoapplicant.model.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Metrics {
  private Double average;
  private Double deviationStandard;
}
