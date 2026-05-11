package com.example.umc._th.domain.member.repository;

import com.example.umc._th.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByEmail(String email);
}