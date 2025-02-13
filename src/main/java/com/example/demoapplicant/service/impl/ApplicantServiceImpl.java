package com.example.demoapplicant.service.impl;

import com.example.demoapplicant.config.RabbitMQConfig;
import com.example.demoapplicant.model.api.Applicant;
import com.example.demoapplicant.model.entity.ApplicantEntity;
import com.example.demoapplicant.repository.ApplicantRepository;
import com.example.demoapplicant.service.ApplicantService;
import com.example.demoapplicant.util.Util;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicantServiceImpl implements ApplicantService {

  @Autowired
  private ApplicantRepository repository;

  @Autowired
  private RabbitTemplate template;

  @Override
  public Applicant saveApplicant(Applicant applicant) {
    ApplicantEntity entity = repository.save(this.toEntity(applicant));
    template.convertAndSend(RabbitMQConfig.EXCHANGE, entity);
    return this.toModel(entity);
  }

  @Override
  public List<Applicant> getAll() {
    return repository.findAll()
        .stream()
        .map(this::toModel)
        .toList();
  }

  private ApplicantEntity toEntity(Applicant applicant) {
    ApplicantEntity entity = new ApplicantEntity();
    entity.setName(applicant.getName());
    entity.setLastname(applicant.getLastname());
    entity.setBirthdate(applicant.getBirthdate());
    return entity;
  }

  private Applicant toModel(ApplicantEntity entity) {
    return Applicant.builder()
        .id(entity.getId())
        .name(entity.getName())
        .lastname(entity.getLastname())
        .age(Util.obtainAge(entity.getBirthdate()))
        .lifeExpectancy(Util.obtainLifeExpectancy(entity.getBirthdate()))
        .birthdate(entity.getBirthdate())
        .build();
  }
}
