package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionCompleteResDTO;
import com.example.umc10th.domain.mission.dto.MissionListResDTO;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/{userId}/missions")
public class MissionController {

    private final MissionService missionService;

    // 미션 목록 조회 (페이징)
    @GetMapping
    public ApiResponse<MissionListResDTO> getMissions(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        MissionListResDTO response =
                missionService.getMyMissions(userId, page, size);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }

    // 미션 완료
    @PatchMapping("/{userMissionId}/complete")
    public ApiResponse<MissionCompleteResDTO> completeMission(
            @PathVariable Long userId,
            @PathVariable Long userMissionId
    ) {

        MissionCompleteResDTO response =
                new MissionCompleteResDTO(userMissionId, "COMPLETED");

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }
}