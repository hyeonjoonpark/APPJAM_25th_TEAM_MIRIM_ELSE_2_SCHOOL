package org.appjam.project.domain.user.presentatioin.dto.request;

import lombok.Builder;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Builder
public record UserInfoRequest(
  String nickname,
  LocalDate birth,
  LocalDate firstMeetDate
) {

}
