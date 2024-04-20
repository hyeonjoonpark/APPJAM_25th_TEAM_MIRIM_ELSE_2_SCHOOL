package org.appjam.project.domain.couple.repository;

import org.appjam.project.domain.couple.Couple;
import org.appjam.project.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoupleRepository extends JpaRepository<Couple, Long> {

  @Query("SELECT c FROM Couple c WHERE c.malePerson.id = :maleUserId")
  Optional<Couple> findByMaleUserId(@Param("maleUserId") Long maleUserId);

}
