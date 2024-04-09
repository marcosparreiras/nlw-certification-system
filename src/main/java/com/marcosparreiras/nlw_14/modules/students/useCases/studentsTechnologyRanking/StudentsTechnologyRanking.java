package com.marcosparreiras.nlw_14.modules.students.useCases.studentsTechnologyRanking;

import com.marcosparreiras.nlw_14.modules.students.repositories.CertificationStudentRepository;
import com.marcosparreiras.nlw_14.modules.students.repositories.StudentRepository;
import com.marcosparreiras.nlw_14.modules.students.useCases.studentsTechnologyRanking.dtos.StudentsTechnologyRankingRequest;
import com.marcosparreiras.nlw_14.modules.students.useCases.studentsTechnologyRanking.dtos.StudentsTechnologyRankingResponse;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentsTechnologyRanking {

  @Autowired
  private CertificationStudentRepository certificationStudentRepository;

  @Autowired
  private StudentRepository studentRepository;

  public StudentsTechnologyRankingResponse execute(
    StudentsTechnologyRankingRequest requestDTO
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

    return new StudentsTechnologyRankingResponse(studentsEmail);
  }
}
