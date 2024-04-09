package com.marcosparreiras.nlw_14.modules.students.repositories;

import com.marcosparreiras.nlw_14.modules.students.entities.AnswersCertificationEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswersCertificationRepository
  extends JpaRepository<AnswersCertificationEntity, UUID> {}
