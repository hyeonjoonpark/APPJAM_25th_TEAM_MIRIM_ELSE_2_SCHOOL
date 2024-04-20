package org.appjam.project.domain.question.service;

import lombok.RequiredArgsConstructor;
import org.appjam.project.domain.question.Question;
import org.appjam.project.domain.question.presentation.dto.request.QuestionIdRequest;
import org.appjam.project.domain.question.presentation.dto.response.QuestionResponse;
import org.appjam.project.domain.question.repository.QuestionRepository;
import org.appjam.project.domain.question.types.SelectedType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {
  private final QuestionRepository questionRepository;

  @Transactional(readOnly = true)
  public List<QuestionResponse> getAllQuestions() {
    List<Question> questions = questionRepository.findAll(); // Question 목록 조회

    // Question 목록을 QuestionResponse DTO로 변환
    return questions.stream()
      .map(question -> QuestionResponse.builder()
        .firstOption(question.getFirstOption()) // Question 엔티티에서 첫 번째 옵션을 가져옴
        .secondOption(question.getSecondOption()) // Question 엔티티에서 두 번째 옵션을 가져옴
        .build())
      .collect(Collectors.toList()); // 스트림을 List로 변환
  }

  @Transactional
  public ResponseEntity<String> selectOption(QuestionIdRequest dto) {
    questionRepository.findById(dto.id()).ifPresent(question -> {
      if (dto.id() == 1) {
        question.setWhichSelected(SelectedType.FIRST);
      } else {
        question.setWhichSelected(SelectedType.SECOND);
      }
      questionRepository.save(question);
    });
    return ResponseEntity.ok("옵션을 선택하였습니다");
  }
}
