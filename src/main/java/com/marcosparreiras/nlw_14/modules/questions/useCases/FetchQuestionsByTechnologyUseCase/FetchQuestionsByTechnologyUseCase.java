package com.marcosparreiras.nlw_14.modules.questions.useCases.FetchQuestionsByTechnologyUseCase;

import com.marcosparreiras.nlw_14.modules.questions.repositories.QuestionRepository;
import com.marcosparreiras.nlw_14.modules.questions.useCases.FetchQuestionsByTechnologyUseCase.dtos.FetchQuestionsByTechnologyUseCaseRequestDTO;
import com.marcosparreiras.nlw_14.modules.questions.useCases.FetchQuestionsByTechnologyUseCase.dtos.FetchQuestionsByTechnologyUseCaseResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FetchQuestionsByTechnologyUseCase {

  @Autowired
  QuestionRepository questionRepository;

  public FetchQuestionsByTechnologyUseCaseResponseDTO execute(
    FetchQuestionsByTechnologyUseCaseRequestDTO requestDTO
  ) {
    var questions =
      this.questionRepository.findByTechnology(requestDTO.technology());
    return new FetchQuestionsByTechnologyUseCaseResponseDTO(questions);
  }
}
