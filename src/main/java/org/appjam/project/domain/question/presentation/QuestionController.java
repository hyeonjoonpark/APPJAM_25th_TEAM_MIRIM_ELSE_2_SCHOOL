package org.appjam.project.domain.question.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.appjam.project.domain.question.presentation.dto.request.QuestionIdRequest;
import org.appjam.project.domain.question.presentation.dto.response.QuestionResponse;
import org.appjam.project.domain.question.service.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
@Tag(name = "question", description = "밸런스 게임 API")
@RequiredArgsConstructor
public class QuestionController {
  private final QuestionService questionService;
  @GetMapping("/get")
  @Operation(summary = "Get a question", description = "모든 질문 가져오기")
  public List<QuestionResponse> read() {
    List<QuestionResponse> result = questionService.getAllQuestions();
    return result;
  }

  @PutMapping("/question_select")
  @Operation(summary = "Select a enswer", description = "질문에 대한 답 선택하기")
  public ResponseEntity<String> select(@RequestBody QuestionIdRequest dto) {
   questionService.selectOption(dto);
   return ResponseEntity.ok().build();
  }
}
