package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {
    private final MissionService missionService;

    // 미션 목록 조회
    @GetMapping
    public ApiResponse<MissionResDTO.MissionList> getMissions(
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        BaseSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getMissions(status, page, size));
    }

    // 미션 도전하기
    @PostMapping("/{missionId}/challenges")
    public ApiResponse<MissionResDTO.Challenge> challengeMission(
            @PathVariable Long missionId,
            @RequestHeader("Authorization") String accessToken,
            @RequestBody MissionReqDTO.Challenge dto
    ) {
        BaseSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.challengeMission(missionId, dto));
    }
}
