package com.marcosparreiras.nlw_14.modules.questions.controllers;

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

  @GetMapping("/technology/{technology}")
  public ResponseEntity<Object> fetchQuestions(
    @PathVariable String technology
  ) {
    var requestDTO = new FetchQuestionsByTechnologyUseCaseRequestDTO(
      technology
    );
    var questions = this.fetchQuestionsByTechnologyUseCase.execute(requestDTO);
    return ResponseEntity.ok().body(questions);
  }
}
