package org.appjam.project.domain.couple.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.appjam.project.domain.couple.presentation.dto.request.CoupleRequest;
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
  public ResponseEntity<String> addCouple(@RequestBody CoupleRequest dto) {
    coupleService.addCouple(dto);
    return ResponseEntity.ok("성공적으로 초대를 하였습니다");
  }
}
