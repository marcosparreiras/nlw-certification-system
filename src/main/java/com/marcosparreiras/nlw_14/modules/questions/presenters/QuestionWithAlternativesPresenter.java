package com.marcosparreiras.nlw_14.modules.questions.presenters;

import com.marcosparreiras.nlw_14.modules.questions.entities.QuestionEntity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import org.springframework.stereotype.Service;

@Service
public class QuestionWithAlternativesPresenter {

  @Builder
  private record AlternativePresenter(String id, String description) {}

  @Builder
  private record QuestionPresenter(
    String id,
    String technology,
    String description,
    LocalDateTime createdAt,
    List<AlternativePresenter> alternatives
  ) {}

  public List<QuestionPresenter> fromQuestionEntityList(
    List<QuestionEntity> questions
  ) {
    List<QuestionPresenter> questionsList = new ArrayList<>();
    questions.forEach(question -> {
      List<AlternativePresenter> alternativesList = new ArrayList<>();

      question
        .getAlternatives()
        .forEach(alternavite -> {
          var alternativePresenter = AlternativePresenter
            .builder()
            .id(alternavite.getId().toString())
            .description(alternavite.getDescription())
            .build();

          alternativesList.add(alternativePresenter);
        });

      var questionPresenter = QuestionPresenter
        .builder()
        .id(question.getId().toString())
        .description(question.getDescription())
        .createdAt(question.getCreatedAt())
        .technology(question.getTechnology())
        .alternatives(alternativesList)
        .build();

      questionsList.add(questionPresenter);
    });

    return questionsList;
  }
}
