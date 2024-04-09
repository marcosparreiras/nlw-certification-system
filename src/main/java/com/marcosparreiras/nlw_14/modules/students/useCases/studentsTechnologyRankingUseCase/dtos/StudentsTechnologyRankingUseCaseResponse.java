package com.marcosparreiras.nlw_14.modules.students.useCases.studentsTechnologyRankingUseCase.dtos;

import java.util.List;

public record StudentsTechnologyRankingUseCaseResponse(
  List<StudentScore> users
) {}
