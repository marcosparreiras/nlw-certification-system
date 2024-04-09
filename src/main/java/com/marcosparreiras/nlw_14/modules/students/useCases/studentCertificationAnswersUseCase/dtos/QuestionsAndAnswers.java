package com.marcosparreiras.nlw_14.modules.students.useCases.studentCertificationAnswersUseCase.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionsAndAnswers {

  private String questionId;
  private String answerId;
  private boolean isCorrect;
}
