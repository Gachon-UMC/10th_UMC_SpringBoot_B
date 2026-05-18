package com.example.umc10th.domain.member.repository;

import com.example.umc10th.domain.member.entity.mapping.MemberTerm;
import org.springframework.data.jpa.repository.JpaRepository;

// 회원 약관 동의 매핑 Entity의 DB 접근을 담당하는 Repository입니다.
public interface MemberTermRepository extends JpaRepository<MemberTerm, Long> {
}
