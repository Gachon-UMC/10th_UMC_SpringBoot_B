package org.example.umc10thyongjae.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.example.umc10thyongjae.domain.mission.dto.response.UserMissionResponseDto;
import org.example.umc10thyongjae.domain.mission.entity.Mission;
import org.example.umc10thyongjae.domain.mission.entity.UserMission;
import org.example.umc10thyongjae.domain.mission.enums.MissionStatus;
import org.example.umc10thyongjae.domain.mission.repository.UserMissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {
    private final UserMissionRepository userMissionRepository;

    public List<UserMissionResponseDto> getUserMission(long userId, int page, int size, String status) {
        MissionStatus paramStatus = null;

        try {
            paramStatus = MissionStatus.valueOf(status);
        } catch (Exception e) {

        }

        List<UserMissionResponseDto> result =
                userMissionRepository.findUserMissionByUserId(userId, paramStatus, size, page * size)
                        .stream()
                        .map(MissionService::from)
                        .toList();

        return result;
    }


    public static UserMissionResponseDto from(UserMission um) {
        Mission mission = um.getMission();
        return UserMissionResponseDto.builder()
                .missionKey(mission.getMissionId())
                .storeKey(mission.getStore().getStoreId())
                .storeName(mission.getStore().getName())
                .storeCategory(mission.getStore().getCategory())
                .reward(mission.getReward())
                .expireDate(mission.getExpireDate().toString())
                .status(um.getStatus())
                .build();
    }
}
