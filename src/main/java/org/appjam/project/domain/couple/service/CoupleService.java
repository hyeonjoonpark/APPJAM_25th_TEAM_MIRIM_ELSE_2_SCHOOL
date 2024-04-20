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

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CoupleService {
  private final UserRepository userRepository;
  private final CoupleRepository coupleRepository;

  @Transactional
  public ResponseEntity<String> addCouple(CoupleRequest dto) {
    Optional<User> userOptional = Optional.ofNullable(userRepository.findByUsername(dto.username()));
    Optional<User> opponentOptional = Optional.ofNullable(userRepository.findByUsername(dto.opponentName()));

    if (userOptional.isEmpty() || opponentOptional.isEmpty()) {
      return ResponseEntity.badRequest().body("존재하지 않는 닉네임입니다.");
    }

    User user = userOptional.get();
    User opponent = opponentOptional.get();

    // 이미 커플 관계인지 확인
    if (user.getCouple() != null || opponent.getCouple() != null) {
      return ResponseEntity.badRequest().body("이미 커플 관계입니다.");
    }

    Couple couple = createCoupleBasedOnGender(user, opponent);
    coupleRepository.save(couple);

    return ResponseEntity.ok().body("커플이 추가되었습니다");
  }

  private Couple createCoupleBasedOnGender(User user, User opponent) {
    if (user.getGender().equals(GenderType.MALE)) {
      return Couple.builder()
        .malePerson(user)
        .femalePerson(opponent)
        .build();
    } else {
      return Couple.builder()
        .malePerson(opponent)
        .femalePerson(user)
        .build();
    }
  }
}
