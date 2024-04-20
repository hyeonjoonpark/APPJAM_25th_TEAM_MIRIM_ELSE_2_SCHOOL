package org.appjam.project.domain.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.appjam.project.domain.couple.Couple;
import org.appjam.project.domain.user.types.GenderType;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
  @Id
  @Column(name = "user_id")
  private String id;
  private String provider;
  private String providerId;
  private String username;
  private String email;
  private Date birth;
  @Enumerated(EnumType.STRING)
  private GenderType gender;
  private int point;
  private LocalDate firstMeetDate;

  private String invitedCode;

  public void setInvitedCode(String invitedCode) {
    this.invitedCode = invitedCode;
  }

  @OneToOne
  @JoinColumn(name = "couple_id")
  private Couple couple;

  @Builder
  public User(String id, String provider, String providerId, String username, String email, Date birth, GenderType gender, int point, LocalDate firstMeetDate, String invitedCode, Couple couple) {
    this.id = id;
    this.provider = provider;
    this.providerId = providerId;
    this.username = username;
    this.email = email;
    this.birth = birth;
    this.gender = gender;
    this.point = point;
    this.firstMeetDate = firstMeetDate;
    this.invitedCode = invitedCode;
    this.couple = couple;
  }
}
