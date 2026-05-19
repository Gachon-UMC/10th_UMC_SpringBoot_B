package org.example.umc10thyongjae.domain.auth.repository;

import org.example.umc10thyongjae.domain.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
