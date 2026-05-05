package org.example.umc10thyongjae.domain.mission.repository;

import org.example.umc10thyongjae.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {
}
