package com.example.demoapplicant.service.impl;

import com.example.demoapplicant.model.api.Metrics;
import com.example.demoapplicant.model.entity.ApplicantEntity;
import com.example.demoapplicant.repository.ApplicantRepository;
import com.example.demoapplicant.util.MockUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class MetricsServiceImplTest {

  @Mock
  private ApplicantRepository repository;

  @InjectMocks
  private MetricsServiceImpl service;

  @Test
  void whenObtainAlmostOneApplicantThenObtainAgeAverage() {
    //Arrange
    ApplicantEntity entity = MockUtil.obtainApplicantEntity();
    List<ApplicantEntity> list = new ArrayList<>();
    list.add(entity);
    Mockito.when(repository.findAll())
        .thenReturn(list);
    //Act
    Metrics response = service.obtainAllAverageAge();
    //Assert
    Assertions.assertTrue(response.getAverage() > 0);
  }

  @Test
  void whenObtainNoneApplicantThenObtainZeroAverage() {
    //Arrange
    List<ApplicantEntity> list = new ArrayList<>();
    Mockito.when(repository.findAll())
        .thenReturn(list);
    //Act
    Metrics response = service.obtainAllAverageAge();
    //Assert
    Assertions.assertEquals(Double.NaN, response.getAverage());
  }

  @Test
  void whenObtainAlmostOneApplicantThenObtainStandardDeviation() {
    //Arrange
    ApplicantEntity entity = MockUtil.obtainApplicantEntity();
    List<ApplicantEntity> list = new ArrayList<>();
    list.add(entity);
    Mockito.when(repository.findAll())
        .thenReturn(list);
    //Act
    Metrics response = service.obtainAllStandardDeviation();
    //Assert
    Assertions.assertTrue(response.getDeviationStandard() > -1);
  }

  @Test
  void whenObtainNoneApplicantThenObtainZeroStandardDeviation() {
    //Arrange
    List<ApplicantEntity> list = new ArrayList<>();
    Mockito.when(repository.findAll())
        .thenReturn(list);
    //Act
    Metrics response = service.obtainAllStandardDeviation();
    //Assert
    Assertions.assertEquals(Double.NaN, response.getDeviationStandard());
  }
}