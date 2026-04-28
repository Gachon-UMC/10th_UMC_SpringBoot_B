package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.domain.user.exception.code.UserSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mission")
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/missions/{missionId}")
    public ApiResponse<MissionResDTO.GetMission> getMission(
            @PathVariable Long missionId
    ){
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code,missionService.GetMission(missionId));
    }

    @PatchMapping("/user-missions/{userMissionId}/success")
    public ApiResponse<MissionResDTO.SuccessMission> successMission(
        @PathVariable Long userMissionId
    ){
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code,missionService.SeuccessMission(userMissionId));
    }
}
