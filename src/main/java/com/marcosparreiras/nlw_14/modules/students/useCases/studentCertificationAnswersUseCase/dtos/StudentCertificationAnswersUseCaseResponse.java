package com.marcosparreiras.nlw_14.modules.students.useCases.studentCertificationAnswersUseCase.dtos;

import com.marcosparreiras.nlw_14.modules.students.entities.CertificationStudentEntity;
import lombok.Builder;

@Builder
public record StudentCertificationAnswersUseCaseResponse(
  CertificationStudentEntity certification
) {}
