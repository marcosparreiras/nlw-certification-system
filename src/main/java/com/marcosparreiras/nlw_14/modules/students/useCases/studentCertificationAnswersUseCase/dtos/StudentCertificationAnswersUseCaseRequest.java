package com.marcosparreiras.nlw_14.modules.students.useCases.studentCertificationAnswersUseCase.dtos;

import java.util.List;
import lombok.Builder;

@Builder
public record StudentCertificationAnswersUseCaseRequest(
  String email,
  String technology,
  List<QuestionsAndAnswers> questionsAndAnswers
) {}
