package org.example.umc10thyongjae.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.example.umc10thyongjae.domain.mission.dto.response.MissionResponseDto;
import org.example.umc10thyongjae.domain.mission.dto.response.UserMissionResponseDto;
import org.example.umc10thyongjae.domain.mission.entity.Mission;
import org.example.umc10thyongjae.domain.mission.entity.UserMission;
import org.example.umc10thyongjae.domain.mission.enums.MissionStatus;
import org.example.umc10thyongjae.domain.mission.repository.MissionRepository;
import org.example.umc10thyongjae.domain.mission.repository.UserMissionRepository;
import org.example.umc10thyongjae.global.dto.PaginationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {
    private final UserMissionRepository userMissionRepository;
    private final MissionRepository missionRepository;

    public PaginationDto<UserMissionResponseDto> getUserMission(long userId, int page, int size, String status) {
        MissionStatus paramStatus = null;

        try {
            paramStatus = MissionStatus.valueOf(status);
        } catch (Exception e) {

        }

        Page<UserMission> pageData =
                userMissionRepository.findUserMissionByUserId(userId, paramStatus.name(), PageRequest.of(page, size));

        PaginationDto<UserMissionResponseDto> result =
                PaginationDto.<UserMissionResponseDto>builder()
                        .page(pageData.getNumber())
                        .size(pageData.getSize())
                        .hasNext(pageData.hasNext())
                        .data(
                                pageData.map(MissionService::convertUserMission)
                                        .getContent()
                        )
                        .build();
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
