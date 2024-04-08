package com.marcosparreiras.nlw_14.modules.students.controllers;

import com.marcosparreiras.nlw_14.modules.students.useCases.verifyIfHasCertificationUseCase.VerifyIfHasCertificationUseCase;
import com.marcosparreiras.nlw_14.modules.students.useCases.verifyIfHasCertificationUseCase.dtos.VerifyIfHasCertificationUseCaseRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class verifyIfStudentHasCertificationController {

  @Autowired
  VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;

  private record ReqBody(String email, String technology) {}

  @PostMapping("/verify-if-has-certification")
  public ResponseEntity<Object> verify(@RequestBody ReqBody body) {
    var requestDTO = new VerifyIfHasCertificationUseCaseRequestDTO(
      body.email,
      body.technology()
    );
    var result = this.verifyIfHasCertificationUseCase.execute(requestDTO);
    return ResponseEntity.ok().body(result);
  }
}
