package org.appjam.project.domain.user.service;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.appjam.project.domain.user.User;
import org.appjam.project.domain.user.presentatioin.dto.request.PointAddRequest;
import org.appjam.project.domain.user.presentatioin.dto.request.UserInfoRequest;
import org.appjam.project.domain.user.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

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

    String randomUUID = UUID.randomUUID().toString();
    String invitedCode = dto.nickname() + "." + randomUUID;

    user = User.builder()
      .nickname(dto.nickname())
      .birth(dto.birth())
      .firstMeetDate(dto.firstMeetDate())
      .invitedCode(invitedCode)
      .build();

    userRepository.save(user);
    return ResponseEntity.ok("성공적으로 추가정보를 추가하였습니다");
  }

  @Transactional
  public ResponseEntity<String> add(PointAddRequest dto) {
    String userName = dto.userName();
    int point = dto.point();

    User user = userRepository.findByUsername(userName);

    if(user == null) {
      return ResponseEntity.badRequest().body("존재하지 않는 사용자입니다.");
    }

    user.setPoint(point);
    userRepository.save(user);
    return ResponseEntity.ok("포인트를 추가하였습니다.");
  }
}
