package com.marcosparreiras.nlw_14.modules.students.repositories;

import com.marcosparreiras.nlw_14.modules.students.entities.CertificationStudentEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificationStudentRepository
  extends JpaRepository<CertificationStudentEntity, UUID> {}
