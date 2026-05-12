package com.example.umc10th.domain.member.repository;

import com.example.umc10th.domain.member.entity.Term;
import org.springframework.data.jpa.repository.JpaRepository;

// 약관 Entity의 DB 접근을 담당하는 Repository입니다.
public interface TermRepository extends JpaRepository<Term, Long> {
}
