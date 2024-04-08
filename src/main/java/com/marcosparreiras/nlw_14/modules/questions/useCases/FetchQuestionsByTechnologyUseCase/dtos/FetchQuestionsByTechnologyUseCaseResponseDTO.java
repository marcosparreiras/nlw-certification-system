package com.marcosparreiras.nlw_14.modules.questions.useCases.FetchQuestionsByTechnologyUseCase.dtos;

import com.marcosparreiras.nlw_14.modules.questions.entities.QuestionEntity;
import java.util.List;

public record FetchQuestionsByTechnologyUseCaseResponseDTO(
  List<QuestionEntity> questions
) {}
