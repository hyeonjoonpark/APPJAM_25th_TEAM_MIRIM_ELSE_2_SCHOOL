package org.appjam.project.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.appjam.project.domain.user.User;
import org.appjam.project.domain.user.presentatioin.dto.request.UserInfoRequest;
import org.appjam.project.domain.user.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  @Transactional
  public ResponseEntity<String> create(UserInfoRequest dto) {
    User user = userRepository.findByNickname(dto.nickname());

    if (user!= null) {
      return ResponseEntity.badRequest().body("이미 존재하는 닉네임입니다");
    }

    user = User.builder()
      .nickname(dto.nickname())
      .birth(dto.birth())
      .firstMeetDate(dto.firstMeetDate())
      .build();

    userRepository.save(user);
    return ResponseEntity.ok("성공적으로 추가정보를 추가하였습니다");
  }
}
