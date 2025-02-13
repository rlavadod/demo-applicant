package com.example.demoapplicant.service.impl;

import com.example.demoapplicant.model.api.Applicant;
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
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ApplicantServiceImplTest {

  @Mock
  private ApplicantRepository repository;

  @Mock
  private RabbitTemplate template;

  @InjectMocks
  private ApplicantServiceImpl service;

  @Test
  void whenApplicantIsCompleteThenTheApplicantSaveSuccessful() {
    //Arrange
    Applicant applicant = MockUtil.obtainApplicant();
    ApplicantEntity entity = MockUtil.obtainApplicantEntity();
    Mockito.when(repository.save(Mockito.any(ApplicantEntity.class)))
        .thenReturn(entity);
    Mockito.doNothing()
        .when(template)
        .convertAndSend(Mockito.anyString(), Mockito.any(ApplicantEntity.class));
    //Act
    Applicant response = service.saveApplicant(applicant);
    //Assert
    Assertions.assertEquals(applicant.getId(), response.getId());
  }

  @Test
  void whenApplicantIsSavedThenObtainAllApplicants() {
    //Arrange
    ApplicantEntity entity = MockUtil.obtainApplicantEntity();
    List<ApplicantEntity> list = new ArrayList<>();
    list.add(entity);
    Mockito.when(repository.findAll())
        .thenReturn(list);
    //Act
    List<Applicant> response = service.getAll();
    //Assert
    Assertions.assertEquals(list.size(), response.size());
  }
}