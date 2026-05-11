package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionServiceImpl;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/missions")
public class MissionController {

    private final MissionServiceImpl missionService;


    //유저 미션 조회 페이지
    @GetMapping("/{userId}")
    public ApiResponse<Page<MissionResDTO.GetMission>> getMissions(
            @PathVariable Long userId,
            @RequestParam(defaultValue="1") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code,missionService.getMissions(userId,page,size));
    }

    //홈 미션 조회 페이지
    @GetMapping("/availableHomeMission")
    public ApiResponse<Page<MissionResDTO.HomeMission>> getHomeMissions(
            @RequestParam Long regionId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.OK,
                missionService.getHomeMissions(regionId, page, size)
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
