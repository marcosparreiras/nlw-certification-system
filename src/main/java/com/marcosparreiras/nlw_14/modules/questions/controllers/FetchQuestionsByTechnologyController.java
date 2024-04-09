package com.marcosparreiras.nlw_14.modules.questions.controllers;

import com.marcosparreiras.nlw_14.modules.questions.presenters.QuestionWithAlternativesPresenter;
import com.marcosparreiras.nlw_14.modules.questions.useCases.FetchQuestionsByTechnologyUseCase.FetchQuestionsByTechnologyUseCase;
import com.marcosparreiras.nlw_14.modules.questions.useCases.FetchQuestionsByTechnologyUseCase.dtos.FetchQuestionsByTechnologyUseCaseRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/questions")
public class FetchQuestionsByTechnologyController {

  @Autowired
  FetchQuestionsByTechnologyUseCase fetchQuestionsByTechnologyUseCase;

  @Autowired
  QuestionWithAlternativesPresenter questionWithAlternativesPresenter;

  @GetMapping("/technology/{technology}")
  public ResponseEntity<Object> fetchQuestions(
    @PathVariable String technology
  ) {
    var requestDTO = new FetchQuestionsByTechnologyUseCaseRequestDTO(
      technology
    );
    var responseDTO =
      this.fetchQuestionsByTechnologyUseCase.execute(requestDTO);

    var presenter =
      this.questionWithAlternativesPresenter.fromQuestionEntityList(
          responseDTO.questions()
        );

    return ResponseEntity.ok().body(presenter);
  }
}
