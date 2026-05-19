package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;

public interface MissionService {
    Page<MissionResDTO.GetMission> getMissions(
            Long userId, int page, int size);

    MissionResDTO.OffsetPagination<MissionResDTO.GetHomeMission> getHomeMissions(
            Long regionId, int page, int size, String sort);

    MissionResDTO.OffsetPagination<MissionResDTO.GetMission> getInProgressMissions(
            Long regionId, int page, int size, String sort);

    MissionResDTO.CursorPagination<MissionResDTO.GetHomeMission> getStoreMissions(
            Long storeId, int size, String cursor, String query);


}
