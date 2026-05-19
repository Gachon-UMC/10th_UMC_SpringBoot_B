package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.domain.mission.enums.UserMissionStatus;
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.mission.repository.UserMissionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class MissionServiceImpl implements MissionService {

    private final UserMissionRepository userMissionRepository;
    private final MissionRepository missionRepository;

    //유저 미션 조회
    @Override
    public Page<MissionResDTO.GetMission> getMissions(
            Long userId, int page, int size
    ){

        PageRequest pageRequest = PageRequest.of(page, size);

        Page<UserMission> userMissionPage=
                userMissionRepository.findMissionsByUserId(userId,pageRequest);

        return userMissionPage.map(MissionConverter::toGetMission);
    }
    //홈 미션 조회
    @Override
    public MissionResDTO.OffsetPagination<MissionResDTO.GetHomeMission> getHomeMissions(
            Long regionId,
            int page,
            int size,
            String sort
    ) {
        Sort sortInfo;

        if (sort!=null){
            sortInfo = Sort.by(sort);
        }else{
            sortInfo = Sort.by("id").descending();
        }

        //페이지 정보들을 PageRequest로 만들기
        PageRequest pageRequest = PageRequest.of(page, size, sortInfo);

        //홈 화면 미션들 조회
        Page<Mission> homeMissionPage =
                missionRepository.findAvailableMissionsByRegionId(regionId, pageRequest);

        //미션들 응갑 DTO로 포장하기
        return MissionConverter.toOffsetPagination(
                homeMissionPage.map(MissionConverter::toGetHomeMission).toList(),
                homeMissionPage.getNumber(),
                homeMissionPage.getSize()
        );
    }
    //진행중인 미션 조회
    @Override
    public MissionResDTO.OffsetPagination<MissionResDTO.GetMission> getInProgressMissions(
            Long userId,
            int page,
            int size,
            String sort
    ){
        Sort sortInfo;

        if (sort!=null){
            sortInfo = Sort.by(sort); //이러면 안되지 않나?
        }else{
            sortInfo = Sort.by("id").descending();
        }

        PageRequest pageRequest = PageRequest.of(page, size, sortInfo);

        Page<UserMission> inProgressMissionPage =
                userMissionRepository.findMissionsByUserIdAndStatus(userId,UserMissionStatus.IN_PROGRESS,pageRequest);

        return MissionConverter.toOffsetPagination(
                inProgressMissionPage.map(MissionConverter::toGetMission).toList(),
                inProgressMissionPage.getNumber(),
                inProgressMissionPage.getSize()
        );

    }

    //가게 기반 미션 조회
    public MissionResDTO.CursorPagination<MissionResDTO.GetHomeMission> getStoreMissions(
            Long storeId, int size, String cursor, String query)
    {
        PageRequest pageRequest = PageRequest.of(0,size);

        Slice<Mission> missionList;
        String nextCursor;

        if (cursor != null && !cursor.equals("-1")){

            String[] cursorSplit = cursor.split(":");
            switch(query.toLowerCase()){
                case "id" :

                    Long prevCursor = Long.parseLong(cursorSplit[0]);
                    Long idCursor = Long.parseLong(cursorSplit[1]);

                    missionList = missionRepository.findMissionsByStoreIdAndLessThanOrderByIdDesc(
                            storeId,
                            idCursor,
                            pageRequest
                    );
                    break;
                default:
                    throw new MissionException(MissionErrorCode.MISSION_NOT_FOUND);
            }
        }else{
            missionList=missionRepository.findMissionByStoreIdOrderByIdDesc(storeId,pageRequest);
        }

        nextCursor = missionList.getContent().getLast().getId()+":"+missionList.getContent().getLast().getId();

        return MissionConverter.toCursorPagination(
                missionList.map(MissionConverter::toGetHomeMission).toList(),
                missionList.hasNext(),
                nextCursor,
                missionList.getSize()
        );
    }
    public MissionResDTO.SuccessMission SeuccessMission(Long userMissionId) {
        return null;
    }
}
