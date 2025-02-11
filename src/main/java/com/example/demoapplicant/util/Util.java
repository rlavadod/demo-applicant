package com.example.demoapplicant.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class Util {
  public static Integer obtainAge(LocalDate birthdate) {
    if (birthdate != null) {
      return Period.between(birthdate, LocalDate.now()).getYears();
    }
    return Constants.ZERO;
  }

  public static String obtainLifeExpectancy(LocalDate birthdate) {
    BigDecimal age = BigDecimal.valueOf(Util.obtainAge(birthdate));

    return age.divide(BigDecimal.valueOf(Constants.RN_COUNT),10, RoundingMode.HALF_UP)
        .toPlainString();
  }

  public static Double obtainStandardDeviation(List<Integer> values) {
    Double mean = values.stream()
        .mapToDouble(Double::valueOf)
        .average()
        .orElse(Double.NaN);
    Double sumOfSquaredDiffs = values.stream()
        .mapToDouble(Double::valueOf)
        .map(x -> (x - mean) * (x - mean))
        .sum();
    return Math.sqrt(sumOfSquaredDiffs / values.size());
  }
}
