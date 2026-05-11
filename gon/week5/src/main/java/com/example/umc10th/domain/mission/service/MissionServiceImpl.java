package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.mission.repository.UserMissionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionServiceImpl implements MissionService {

    private final UserMissionRepository userMissionRepository;
    private final MissionRepository missionRepository;

    @Override
    public Page<MissionResDTO.GetMission> getMissions(
            Long userId, int page, int size
    ){
        PageRequest pageRequest = PageRequest.of(page, size);

        Page<UserMission> userMissionPage=
                userMissionRepository.findMissionsByUserId(userId,pageRequest);

        return userMissionPage.map(MissionConverter::toGetMission);
    }

    @Override
    public Page<MissionResDTO.HomeMission> getHomeMissions(
            Long regionId,
            int page,
            int size
    ) {
        PageRequest pageRequest = PageRequest.of(page, size);

        Page<Mission> missionPage =
                missionRepository.findAvailableMissionsByRegionId(regionId, pageRequest);

        return missionPage.map(MissionConverter::toHomeMission);
    }

    public MissionResDTO.SuccessMission SeuccessMission(Long userMissionId) {
        return null;
    }
}
