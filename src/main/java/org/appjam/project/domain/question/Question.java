package org.appjam.project.domain.question;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.appjam.project.domain.question.types.SelectedType;
import org.appjam.project.domain.user.User;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "question_id")
  private Long id;

  private String firstOption;
  private String secondOption;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User uploader;
  @Enumerated(EnumType.STRING)
  private SelectedType whichSelected;

  @CreationTimestamp
  private LocalDateTime uploadDate = LocalDateTime.now();

  @Builder
  public Question(Long id, String firstOption, String secondOption, User uploader, SelectedType whichSelected, LocalDateTime uploadDate) {
    this.id = id;
    this.firstOption = firstOption;
    this.secondOption = secondOption;
    this.uploader = uploader;
    this.whichSelected = whichSelected;
    this.uploadDate = uploadDate;
  }
}
