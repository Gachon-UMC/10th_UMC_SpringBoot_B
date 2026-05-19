package org.example.umc10thyongjae.domain.auth.repository;

import org.example.umc10thyongjae.domain.auth.entity.Term;
import org.example.umc10thyongjae.domain.auth.enums.TermName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TermRepository extends JpaRepository<Term, Long> {

    Optional<Term> findByTermName(TermName termName);
}
