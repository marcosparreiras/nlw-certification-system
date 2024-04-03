package com.marcosparreiras.nlw_14.modules.students.entities;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertificationStudentEntity {

  private UUID id;
  private UUID studentId;
  private String technology;
  private int great;
  private List<AnswersCertificationEntity> answersCertificationEntities;
}