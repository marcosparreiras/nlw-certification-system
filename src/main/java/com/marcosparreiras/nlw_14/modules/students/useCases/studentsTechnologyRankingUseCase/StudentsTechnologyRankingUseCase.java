package com.marcosparreiras.nlw_14.modules.students.useCases.studentsTechnologyRankingUseCase;

import com.marcosparreiras.nlw_14.modules.students.repositories.CertificationStudentRepository;
import com.marcosparreiras.nlw_14.modules.students.repositories.StudentRepository;
import com.marcosparreiras.nlw_14.modules.students.useCases.studentsTechnologyRankingUseCase.dtos.StudentsTechnologyRankingUseCaseRequest;
import com.marcosparreiras.nlw_14.modules.students.useCases.studentsTechnologyRankingUseCase.dtos.StudentsTechnologyRankingUseCaseResponse;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentsTechnologyRankingUseCase {

  @Autowired
  private CertificationStudentRepository certificationStudentRepository;

  @Autowired
  private StudentRepository studentRepository;

  public StudentsTechnologyRankingUseCaseResponse execute(
    StudentsTechnologyRankingUseCaseRequest requestDTO
  ) {
    var certifications =
      this.certificationStudentRepository.findTop10ByTechnology(
          requestDTO.technology()
        );

    var studentsEmail = certifications
      .stream()
      .map(cert -> {
        var student = this.studentRepository.findById(cert.getStudentId());
        return student.get().getEmail();
      })
      .collect(Collectors.toList());

    return new StudentsTechnologyRankingUseCaseResponse(studentsEmail);
  }
}
