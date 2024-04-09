package com.marcosparreiras.nlw_14.modules.students.controllers;

import com.marcosparreiras.nlw_14.modules.students.useCases.studentsTechnologyRankingUseCase.StudentsTechnologyRankingUseCase;
import com.marcosparreiras.nlw_14.modules.students.useCases.studentsTechnologyRankingUseCase.dtos.StudentsTechnologyRankingUseCaseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentsTechnologyRankingController {

  @Autowired
  private StudentsTechnologyRankingUseCase studentsTechnologyRankingUseCase;

  @GetMapping("/{technology}/ranking")
  public ResponseEntity<Object> ranking(@PathVariable String technology) {
    try {
      var requestDTO = new StudentsTechnologyRankingUseCaseRequest(technology);
      var responseDTO =
        this.studentsTechnologyRankingUseCase.execute(requestDTO);
      return ResponseEntity.ok().body(responseDTO);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
