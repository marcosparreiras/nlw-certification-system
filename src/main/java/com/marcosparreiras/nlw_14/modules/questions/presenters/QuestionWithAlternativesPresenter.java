package com.marcosparreiras.nlw_14.modules.questions.presenters;

import com.marcosparreiras.nlw_14.modules.questions.entities.AlternativesEntitty;
import com.marcosparreiras.nlw_14.modules.questions.entities.QuestionEntity;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import org.springframework.stereotype.Service;

@Service
public class QuestionWithAlternativesPresenter {

  @Builder
  private static record AlternativePresenter(String id, String description) {}

  @Builder
  private static record QuestionPresenter(
    String id,
    String technology,
    String description,
    LocalDateTime createdAt,
    List<AlternativePresenter> alternatives
  ) {}

  public static AlternativePresenter alternativeMapper(
    AlternativesEntitty alternative
  ) {
    return AlternativePresenter
      .builder()
      .id(alternative.getId().toString())
      .description(alternative.getDescription())
      .build();
  }

  public static QuestionPresenter questionMapper(QuestionEntity question) {
    var alternativesPresenter = question
      .getAlternatives()
      .stream()
      .map(alternative -> alternativeMapper(alternative))
      .collect(Collectors.toList());

    return QuestionPresenter
      .builder()
      .id(question.getId().toString())
      .technology(question.getTechnology())
      .description(question.getDescription())
      .createdAt(question.getCreatedAt())
      .alternatives(alternativesPresenter)
      .build();
  }

  public static List<QuestionPresenter> PresenterListfromQuestionEntityList(
    List<QuestionEntity> questions
  ) {
    var questionsList = questions
      .stream()
      .map(question -> questionMapper(question))
      .collect(Collectors.toList());

    return questionsList;
  }
}
