package org.appjam.project.domain.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.appjam.project.domain.user.types.GenderType;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
  @Id
  private String id;
  private String provider;
  private String providerId;
  private String username;
  private String email;
  private Date birth;
  private GenderType gender;
  @CreationTimestamp
  private LocalDate firstMeetDate = LocalDate.now();
  private int point;

  @Builder
  public User(String id, String provider, String providerId, String username, String email, Date birth, GenderType gender, LocalDate firstMeetDate, int point) {
    this.id = id;
    this.provider = provider;
    this.providerId = providerId;
    this.username = username;
    this.email = email;
    this.birth = birth;
    this.gender = gender;
    this.firstMeetDate = firstMeetDate;
    this.point = point;
  }
}
