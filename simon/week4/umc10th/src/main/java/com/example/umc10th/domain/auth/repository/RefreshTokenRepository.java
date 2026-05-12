package com.example.umc10th.domain.auth.repository;

import com.example.umc10th.domain.auth.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    // 사용자 ID로 토큰 찾기 (토큰 재발급 시 사용)
    Optional<RefreshToken> findByMemberId(Long memberId);

    // 사용자 ID로 토큰 삭제 (로그아웃 시 사용)
    void deleteByMemberId(Long memberId);

    // 또는 토큰 값 자체로 삭제 (로그아웃 시 사용)
    void deleteByToken(String token);
}
