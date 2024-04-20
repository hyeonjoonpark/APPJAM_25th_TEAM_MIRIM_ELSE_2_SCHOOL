package org.appjam.project.domain.user.presentatioin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.appjam.project.domain.user.presentatioin.dto.request.UserInfoRequest;
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
  @PostMapping("/info/add")
  @Operation(summary = "Adds a user", description = "유저 추가 정보 추가")
  public ResponseEntity<String> addInfo(@RequestBody UserInfoRequest dto) {


    return ResponseEntity.ok(dto.nickname() + "님의 정보를 추가하였습니다");
  }
}
