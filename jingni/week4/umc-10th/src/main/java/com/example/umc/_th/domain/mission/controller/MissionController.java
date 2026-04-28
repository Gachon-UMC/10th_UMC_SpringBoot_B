package com.example.umc._th.domain.mission.controller;

import com.example.umc._th.domain.mission.dto.MissionDTO;
import com.example.umc._th.domain.mission.dto.MissionResDTO;
import com.example.umc._th.domain.mission.enums.Status;
import com.example.umc._th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc._th.domain.mission.service.MissionService;
import com.example.umc._th.global.apiPayload.ApiResponse;
import com.example.umc._th.global.apiPayload.code.BaseSuccessCode;
import com.example.umc._th.global.enums.SortType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/v1/missions")
    public ApiResponse<MissionResDTO.GetMissions> getMissions(
            @RequestParam("regionId") Long regionId,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sortType", defaultValue = "LATEST") SortType sortType
    ){
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getMissions());
    }

    @GetMapping("/v1/missions/my")
    public ApiResponse<MissionResDTO.GetMissions> getMyMissions(
            @RequestParam("status") Status status,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sortType", defaultValue = "LATEST") SortType sortType
            ){
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getMyMissions());
    }

    @GetMapping("/v1/missions/complete/count")
    public ApiResponse<MissionResDTO.GetCompleteMissionsCnt> getCompleteMissionsCnt(
            @RequestParam("regionId") Long regionId
            ){
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getCompleteMissionsCnt());
    }

    @PatchMapping("/v1/missions/{missionId}/complete")
    public ApiResponse<MissionResDTO.MissionComplete> completeMission(
        @PathVariable("missionId") Long missionId
    ){
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.completeMission());
    }
}
