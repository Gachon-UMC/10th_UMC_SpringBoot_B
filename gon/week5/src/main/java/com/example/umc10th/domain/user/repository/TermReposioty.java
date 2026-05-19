package com.example.umc10th.domain.user.repository;

import com.example.umc10th.domain.user.entity.Term;
import com.example.umc10th.domain.user.enums.TermName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TermReposioty extends JpaRepository<Term,Long> {

    List<Term> findAllByRequiredTrue();
}
