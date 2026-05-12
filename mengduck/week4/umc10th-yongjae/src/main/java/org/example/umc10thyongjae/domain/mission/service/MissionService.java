package org.example.umc10thyongjae.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.example.umc10thyongjae.domain.mission.dto.response.MissionResponseDto;
import org.example.umc10thyongjae.domain.mission.dto.response.UserMissionResponseDto;
import org.example.umc10thyongjae.domain.mission.entity.Mission;
import org.example.umc10thyongjae.domain.mission.entity.UserMission;
import org.example.umc10thyongjae.domain.mission.enums.MissionStatus;
import org.example.umc10thyongjae.domain.mission.repository.MissionRepository;
import org.example.umc10thyongjae.domain.mission.repository.UserMissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {
    private final UserMissionRepository userMissionRepository;
    private final MissionRepository missionRepository;

    public List<UserMissionResponseDto> getUserMission(long userId, int page, int size, String status) {
        MissionStatus paramStatus = null;

        try {
            paramStatus = MissionStatus.valueOf(status);
        } catch (Exception e) {

        }

        List<UserMissionResponseDto> result =
                userMissionRepository.findUserMissionByUserId(userId, paramStatus, size, page * size)
                        .stream()
                        .map(MissionService::convertUserMission)
                        .toList();

        return result;
    }

    public List<MissionResponseDto> getMission(String location, int page, int size) {
        List<MissionResponseDto> result =
                missionRepository.findMissionByLocation(location, size, page * size)
                        .stream()
                        .map(MissionService::convertMission)
                        .toList();

        return result;
    }

    private static UserMissionResponseDto convertUserMission(UserMission um) {
        Mission mission = um.getMission();
        return UserMissionResponseDto.builder()
                .missionKey(mission.getId())
                .storeKey(mission.getStore().getId())
                .storeName(mission.getStore().getName())
                .storeCategory(mission.getStore().getCategory())
                .reward(mission.getReward())
                .expireDate(mission.getExpireDate().toString())
                .status(um.getStatus())
                .build();
    }

    private static MissionResponseDto convertMission(Mission m) {
        return MissionResponseDto.builder()
                .missionId(m.getId())
                .storeId(m.getStore().getId())
                .storeName(m.getStore().getName())
                .storeCategory(m.getStore().getCategory())
                .reward(m.getReward())
                .expireDate(m.getExpireDate().toString())
                .build();
    }
}
