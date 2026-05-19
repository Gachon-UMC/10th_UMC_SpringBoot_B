package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionServiceImpl;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class MissionController {

    private final MissionServiceImpl missionService;


    //유저 미션 조회 페이지
    @GetMapping("/{userId}/missions")
    public ApiResponse<Page<MissionResDTO.GetMission>> getMissions(
            @PathVariable Long userId,
            @RequestParam(defaultValue="0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code,missionService.getMissions(userId,page,size));
    }

    //진행중인 미션 조회하기
    @GetMapping("/{userId}/missions/in-progress")
    public ApiResponse<MissionResDTO.OffsetPagination<MissionResDTO.GetMission>> getInProgressMissoins(
            @RequestBody MissionReqDTO.GetInProgressMission request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sort
    ){
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code,missionService.getInProgressMissions(request.userId(),page,size,sort));
    }

    //가게 내 미션들 조회
    @GetMapping("/stores/{storeId}/missions")
    public ApiResponse <MissionResDTO.CursorPagination<MissionResDTO.GetHomeMission>> getStoresMissions(
            @PathVariable Long storeId,
            @RequestParam int size,
            @RequestParam String cursor,
            @RequestParam String query
    ){
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code,missionService.getStoreMissions(storeId,size,cursor,query));
    }

    //홈 미션 조회 페이지
    @GetMapping("/missions/HomeMission")
    public ApiResponse<MissionResDTO.OffsetPagination<MissionResDTO.GetHomeMission>> getHomeMissions(
            @RequestParam Long regionId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required=false) String sort
            ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.OK,
                missionService.getHomeMissions(regionId, page, size,sort)
        );
    }
    //미션 상태 바꾸기
    @PatchMapping("/{userMissionId}/success")
    public ApiResponse<MissionResDTO.SuccessMission> successMission(
        @PathVariable Long userMissionId
    ){
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code,missionService.SeuccessMission(userMissionId));
    }
}
