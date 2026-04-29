package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;

public class MissionConverter {
    public static MissionResDTO.MissionList toMissionList(Page<MemberMission> memberMissions) {
        return null;
    }
}
