package com.example.umc10th.domain.member.repository;

import com.example.umc10th.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // 이메일로 중복 여부 확인
    boolean existsByEmail(String email);

    // 이메일로 사용자 엔티티 조회 (로그인 시 사용)
    Optional<Member> findByEmail(String email);

    // 사용자 정보 조회
    @Query("select m from Member m where m.id = :memberId and m.memberStatus = 'ACTIVE'")
    Optional<Member> findActiveMemberById(@Param("memberId") Long memberId);
}
