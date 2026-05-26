package org.example.umc10thyongjae.domain.auth.repository;

import org.example.umc10thyongjae.domain.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByLoginId(String loginId);

    Optional<User> findByLoginId(String loginId);
}
