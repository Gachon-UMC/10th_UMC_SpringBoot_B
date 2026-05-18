package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// 회원 미션 매핑 Entity의 DB 접근을 담당하는 Repository입니다.
public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    // 회원 ID와 상태 조건 기준으로 회원 미션과 미션 정보를 함께 조회하는 JPQL입니다.
    @Query("""
            SELECT mm
            FROM MemberMission mm
            JOIN FETCH mm.mission m
            WHERE mm.member.id = :memberId
            AND (:isComplete IS NULL OR mm.isComplete = :isComplete)
            """)
    Page<MemberMission> findByMemberIdAndStatus(
            @Param("memberId") Long memberId,
            @Param("isComplete") Boolean isComplete,
            Pageable pageable
    );

    // 회원이 이미 특정 미션에 도전했는지 확인합니다.
    boolean existsByMemberIdAndMissionId(Long memberId, Long missionId);
}
