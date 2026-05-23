package com.example.umc10th.domain.member.repository;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.enums.SocialType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // 이메일로 중복 여부 확인
    boolean existsByEmail(String email);

    // 이메일로 사용자 엔티티 조회 (로그인 시 사용)
    Optional<Member> findByEmail(String email);

    // 소셜 타입과 고유 UID 조합으로 사용자 엔티티 조회 (카카오 로그인 및 소셜 토큰 파싱 검증 시 사용)
    Optional<Member> findBySocialTypeAndSocialUid(SocialType socialType, String socialUid);
}
