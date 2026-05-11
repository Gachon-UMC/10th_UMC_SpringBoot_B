package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;

public interface MissionService {
    Page<MissionResDTO.GetMission> getMissions(
            Long userId, int page, int size);

    Page<MissionResDTO.HomeMission> getHomeMissions(
            Long regionId, int page, int size);


}
