package com.marcosparreiras.nlw_14.modules.students.useCases.studentCertificationAnswersUseCase;

import com.marcosparreiras.nlw_14.modules.questions.entities.QuestionEntity;
import com.marcosparreiras.nlw_14.modules.questions.repositories.QuestionRepository;
import com.marcosparreiras.nlw_14.modules.students.entities.AnswersCertificationEntity;
import com.marcosparreiras.nlw_14.modules.students.entities.CertificationStudentEntity;
import com.marcosparreiras.nlw_14.modules.students.entities.StudentEntity;
import com.marcosparreiras.nlw_14.modules.students.repositories.AnswersCertificationRepository;
import com.marcosparreiras.nlw_14.modules.students.repositories.CertificationStudentRepository;
import com.marcosparreiras.nlw_14.modules.students.repositories.StudentRepository;
import com.marcosparreiras.nlw_14.modules.students.useCases.studentCertificationAnswersUseCase.dtos.QuestionsAndAnswers;
import com.marcosparreiras.nlw_14.modules.students.useCases.studentCertificationAnswersUseCase.dtos.StudentCertificationAnswersUseCaseRequest;
import com.marcosparreiras.nlw_14.modules.students.useCases.studentCertificationAnswersUseCase.dtos.StudentCertificationAnswersUseCaseResponse;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentCertificationAnswersUseCase {

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private QuestionRepository questionRepository;

  @Autowired
  private CertificationStudentRepository certificationStudentRepository;

  @Autowired
  private AnswersCertificationRepository answersCertificationRepository;

  public StudentCertificationAnswersUseCaseResponse execute(
    StudentCertificationAnswersUseCaseRequest requestDTO
  ) throws Exception {
    var studentOnRepository =
      this.studentRepository.findByEmail(requestDTO.email());
    StudentEntity student = studentOnRepository.get();

    if (studentOnRepository.isEmpty()) {
      var studentEntity = StudentEntity
        .builder()
        .email(requestDTO.email())
        .build();
      student = this.studentRepository.save(studentEntity);
    }

    var questions =
      this.questionRepository.findByTechnology(requestDTO.technology());

    var certification =
      this.certificationStudentRepository.save(
          CertificationStudentEntity
            .builder()
            .technology(requestDTO.technology())
            .studentId(student.getId())
            .build()
        );

    int great = 0;
    for (QuestionsAndAnswers questionAnswer : requestDTO.questionsAndAnswers()) {
      correctQuestion(questionAnswer, questions);
      this.answersCertificationRepository.save(
          AnswersCertificationEntity
            .builder()
            .certificationId(certification.getId())
            .studentId(student.getId())
            .questionId(UUID.fromString(questionAnswer.getQuestionId()))
            .answerId(UUID.fromString(questionAnswer.getAnswerId()))
            .isCorrect(questionAnswer.isCorrect())
            .build()
        );
      if (questionAnswer.isCorrect()) {
        great++;
      }
    }

    certification.setGreat(great);
    certification = this.certificationStudentRepository.save(certification);

    return new StudentCertificationAnswersUseCaseResponse(certification);
  }

  private void correctQuestion(
    QuestionsAndAnswers questionAnswer,
    List<QuestionEntity> technologyQuestions
  ) throws Exception {
    var question = technologyQuestions
      .stream()
      .filter(q -> q.getId().toString() == questionAnswer.getQuestionId())
      .findFirst();
    if (question.isEmpty()) {
      throw new Exception(
        "Questions (" + questionAnswer.getQuestionId() + ") not found"
      );
    }
    var alternative = question
      .get()
      .getAlternatives()
      .stream()
      .filter(alt -> alt.getId().toString() == questionAnswer.getAnswerId())
      .findFirst();
    if (alternative.isEmpty()) {
      throw new Exception(
        "Alternative (" + questionAnswer.getAnswerId() + ") not found"
      );
    }

    questionAnswer.setCorrect(alternative.get().isCorrect());
  }
}
