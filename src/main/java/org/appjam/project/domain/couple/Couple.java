package org.appjam.project.domain.couple;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.appjam.project.domain.user.User;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Couple {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "couple_id")
  private Long id;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "male_user_id", referencedColumnName = "id")
  private User malePerson;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "female_user_id", referencedColumnName = "id")
  private User femalePerson;
  private LocalDate meetDate;
}
