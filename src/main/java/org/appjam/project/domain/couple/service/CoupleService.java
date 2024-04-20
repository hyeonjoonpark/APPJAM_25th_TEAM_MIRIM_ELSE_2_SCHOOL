package org.appjam.project.domain.couple.service;

import lombok.RequiredArgsConstructor;
import org.appjam.project.domain.user.User;
import org.appjam.project.domain.user.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CoupleService {
  private final UserRepository userRepository;
  @Transactional
  public ResponseEntity<String> create(String userName) {
    // 초대코드를 만들어서 초대코드를 받은 사람을 데이터베이스에 추가
    String randomUUID = UUID.randomUUID().toString();
    String invitedCode = userName + "." + randomUUID;
    User user = userRepository.findByUsername(userName);

    user.setInvitedCode(invitedCode);
    userRepository.save(user);
    return ResponseEntity.ok("초대코드가 성공적으로 생성되었습니다");
  }
}
