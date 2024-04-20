package org.appjam.project.domain.couple.service;

import lombok.RequiredArgsConstructor;
import org.appjam.project.domain.couple.Couple;
import org.appjam.project.domain.couple.presentation.dto.request.CoupleRequest;
import org.appjam.project.domain.couple.repository.CoupleRepository;
import org.appjam.project.domain.user.User;
import org.appjam.project.domain.user.repository.UserRepository;
import org.appjam.project.domain.user.types.GenderType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CoupleService {
  private final UserRepository userRepository;
  private final CoupleRepository coupleRepository;
  @Transactional
  public ResponseEntity<String> addCouple(CoupleRequest dto) {
    User user = userRepository.findByNickname(dto.username());
    User opponent = userRepository.findByNickname(dto.opponentName());

    if (user == null || opponent == null) {
      return ResponseEntity.badRequest().body("존재하지 않는 닉네임입니다.");
    }

    // 사용자와 상대방의 성별에 따라 Couple 엔티티를 생성
    Couple couple;
    if (user.getGender().equals(GenderType.MALE)) {
      couple = Couple.builder()
        .malePerson(user)
        .femalePerson(opponent)
        .build();
    } else {
      couple = Couple.builder()
        .malePerson(opponent)
        .femalePerson(user)
        .build();
    }

    // Couple 정보 저장
    coupleRepository.save(couple);

    // 초대코드 생성 및 반환 로직 추가 예정
    // 예시로 UUID를 사용하여 초대코드를 생성하고 반환
    String inviteCode = UUID.randomUUID().toString();
    return ResponseEntity.ok().body("초대코드가 생성되었습니다: " + inviteCode);
  }

}
