package org.appjam.project.domain.question.presentation.dto.response;

import lombok.Builder;

@Builder
public record QuestionResponse(
  String firstOption,
  String secondOption
) {

}
