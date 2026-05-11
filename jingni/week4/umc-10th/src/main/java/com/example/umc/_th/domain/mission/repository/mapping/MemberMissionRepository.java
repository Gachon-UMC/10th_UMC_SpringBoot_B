package com.example.umc._th.domain.mission.repository.mapping;

import com.example.umc._th.domain.member.entity.mapping.MemberMission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
}
