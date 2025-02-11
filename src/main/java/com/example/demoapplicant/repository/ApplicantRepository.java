package com.example.demoapplicant.repository;

import com.example.demoapplicant.model.entity.ApplicantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ApplicantRepository extends JpaRepository<ApplicantEntity, Long> {
}
