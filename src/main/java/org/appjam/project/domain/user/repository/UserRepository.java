package org.appjam.project.domain.user.repository;

import org.appjam.project.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

  User findByUsername(String userName);
}
