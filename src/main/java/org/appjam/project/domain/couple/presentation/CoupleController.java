package org.appjam.project.domain.couple.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.appjam.project.domain.couple.service.CoupleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/couple")
@Tag(name = "Couple", description = "커플 API")
@RequiredArgsConstructor
public class CoupleController {
  private final CoupleService coupleService;
  @PostMapping("/add")
  @Operation(summary = "Create Invited Code", description="초대코드 생성")
  public ResponseEntity<String> addCouple(@RequestBody String userName) {
    // 초대코드를 만들어서 초대코드를 받은 사람을 데이터베이스에 추가
    coupleService.create(userName);
    return ResponseEntity.ok("초대코드를 성공적으로 생성하였습니다");
  }
}
