package org.appjam.project.domain.couple.repository;

import org.appjam.project.domain.couple.Couple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoupleRepository extends JpaRepository<Couple, Long> {

}
