package com.marcosparreiras.nlw_14.modules.students.controllers;

import com.marcosparreiras.nlw_14.modules.students.useCases.studentCertificationAnswersUseCase.StudentCertificationAnswersUseCase;
import com.marcosparreiras.nlw_14.modules.students.useCases.studentCertificationAnswersUseCase.dtos.StudentCertificationAnswersUseCaseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students/certification-answers")
public class StudentCertificationAnswersController {

  @Autowired
  private StudentCertificationAnswersUseCase studentCertificationAnswersUseCase;

  @PostMapping("")
  public ResponseEntity<Object> certificationAnswers(
    @RequestBody StudentCertificationAnswersUseCaseRequest requestDTO
  ) {
    try {
      var result = this.studentCertificationAnswersUseCase.execute(requestDTO);
      return ResponseEntity.status(HttpStatus.CREATED).body(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
