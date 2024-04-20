package org.appjam.project.domain.couple;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.appjam.project.domain.user.User;

import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Couple {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "couple_id")
  private Long id;
  @OneToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "male_user_id", referencedColumnName = "user_id")
  private User malePerson;

  @OneToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "female_user_id", referencedColumnName = "user_id")
  private User femalePerson;


  @Builder
  public Couple(Long id, User malePerson, User femalePerson) {
    this.id = id;
    this.malePerson = malePerson;
    this.femalePerson = femalePerson;
  }
}
