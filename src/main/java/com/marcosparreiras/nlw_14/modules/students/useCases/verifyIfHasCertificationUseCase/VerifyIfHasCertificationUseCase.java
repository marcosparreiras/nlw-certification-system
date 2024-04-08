package com.marcosparreiras.nlw_14.modules.students.useCases.verifyIfHasCertificationUseCase;

import com.marcosparreiras.nlw_14.modules.students.repositories.CertificationStudentRepository;
import com.marcosparreiras.nlw_14.modules.students.useCases.verifyIfHasCertificationUseCase.dtos.VerifyIfHasCertificationUseCaseRequestDTO;
import com.marcosparreiras.nlw_14.modules.students.useCases.verifyIfHasCertificationUseCase.dtos.VerifyIfHasCertificationUseCaseResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerifyIfHasCertificationUseCase {

  @Autowired
  CertificationStudentRepository certificationStudentRepository;

  public VerifyIfHasCertificationUseCaseResponseDTO execute(
    VerifyIfHasCertificationUseCaseRequestDTO requestDTO
  ) {
    var certifications =
      this.certificationStudentRepository.findByStudentEmailAndTechnology(
          requestDTO.email(),
          requestDTO.technology()
        );

    return new VerifyIfHasCertificationUseCaseResponseDTO(
      !certifications.isEmpty()
    );
  }
}
