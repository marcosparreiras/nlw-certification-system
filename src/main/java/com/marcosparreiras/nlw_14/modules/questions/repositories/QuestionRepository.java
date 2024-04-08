package com.marcosparreiras.nlw_14.modules.questions.repositories;

import com.marcosparreiras.nlw_14.modules.questions.entities.QuestionEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository
  extends JpaRepository<QuestionEntity, UUID> {
  List<QuestionEntity> findByTechnology(String technology);
}
