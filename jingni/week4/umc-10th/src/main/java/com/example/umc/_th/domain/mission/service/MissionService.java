package com.example.umc._th.domain.mission.service;

import com.example.umc._th.domain.member.entity.mapping.MemberMission;
import com.example.umc._th.domain.member.exception.MemberException;
import com.example.umc._th.domain.member.exception.code.MemberErrorCode;
import com.example.umc._th.domain.member.repository.MemberRepository;
import com.example.umc._th.domain.mission.dto.MissionDTO;
import com.example.umc._th.domain.mission.dto.MissionResDTO;
import com.example.umc._th.domain.mission.entity.Mission;
import com.example.umc._th.domain.mission.enums.Status;
import com.example.umc._th.domain.mission.exception.MissionException;
import com.example.umc._th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc._th.domain.mission.repository.MissionRepository;
import com.example.umc._th.domain.mission.repository.mapping.MemberMissionRepository;
import com.example.umc._th.domain.region.exception.RegionException;
import com.example.umc._th.domain.region.exception.code.RegionErrorCode;
import com.example.umc._th.domain.region.repository.RegionRepository;
import com.example.umc._th.domain.store.dto.StoreDTO;
import com.example.umc._th.global.enums.SortType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final RegionRepository regionRepository;
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    public MissionResDTO.GetMissions getMissions(Long regionId, Integer page, Integer size, SortType sortType){
        int offset = page * size;

        if (!regionRepository.existsById(regionId)) {
            throw new RegionException(RegionErrorCode.REGION_NOT_FOUND);
        }

        List<Mission> missions = switch (sortType) {
            case REWARD -> missionRepository.findByRegionOrderByPoint(regionId, size, offset);
            case LATEST -> missionRepository.findByRegion(regionId, size, offset);
        };

        if(missions.isEmpty()){
            throw new MissionException(MissionErrorCode.MISSION_NOT_FOUND);
        }

        List<MissionDTO.Mission> result = missions.stream()
                .map(m -> new MissionDTO.Mission(
                        m.getId(),
                        m.getDeadline(),
                        m.getPoint(),
                        null,
                        new StoreDTO.StoreSummary(
                                m.getStore().getName(),
                                m.getStore().getFood().getType()
                        )
                ))
                .toList();
        return new MissionResDTO.GetMissions(result);
    }

    public MissionResDTO.GetMissions getMyMissions(Long memberId, Status status, Integer page, Integer size, SortType sortType) {
        int offset = page * size;

        if(!memberRepository.existsById(memberId)){
            throw new MemberException(MemberErrorCode.MEMBER_NOT_FOUND);
        }

        List<Mission> missions = missionRepository.findMyMissions(
                memberId,
                status != null ? status.name() : null,
                size,
                offset
        );

        if(missions.isEmpty()){
            throw new MissionException(MissionErrorCode.MISSION_NOT_FOUND);
        }

        List<MissionDTO.Mission> result = missions.stream()
                .map(m -> new MissionDTO.Mission(
                        m.getId(),
                        m.getDeadline(),
                        m.getPoint(),
                        status,

                        new StoreDTO.StoreSummary(
                                m.getStore().getName(),
                                m.getStore().getFood().getType()
                        )
                ))
                .toList();
        return new MissionResDTO.GetMissions(result);
    }

    public MissionResDTO.GetCompleteMissionsCnt getCompleteMissionsCnt(Long regionId, Long memberId) {

        if(!memberRepository.existsById(memberId)){
            throw new MemberException(MemberErrorCode.MEMBER_NOT_FOUND);
        }

        if (!regionRepository.existsById(regionId)) {
            throw new RegionException(RegionErrorCode.REGION_NOT_FOUND);
        }

        Integer count = missionRepository.countCompletedMissionsByRegion(
                memberId,
                regionId
        );
        return new MissionResDTO.GetCompleteMissionsCnt(count);
    }
}
