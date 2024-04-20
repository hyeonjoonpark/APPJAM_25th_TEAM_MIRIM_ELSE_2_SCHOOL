package org.appjam.project.domain.user.presentatioin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.appjam.project.domain.user.presentatioin.dto.request.PointAddRequest;
import org.appjam.project.domain.user.presentatioin.dto.request.UserInfoRequest;
import org.appjam.project.domain.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Tag(name = "user", description = "유저 API")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;
  @PostMapping("/info/add")
  @Operation(summary = "Adds a user", description = "유저 추가 정보 추가")
  public ResponseEntity<String> addInfo(@RequestBody UserInfoRequest dto) {
    userService.create(dto);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/point/add")
  @Operation(summary = "Add Points", description = "사용자 포인트 추가")
  public ResponseEntity<String> addPoints(@RequestBody PointAddRequest dto) {
    userService.add(dto);
    return ResponseEntity.ok().build();
  }
}
