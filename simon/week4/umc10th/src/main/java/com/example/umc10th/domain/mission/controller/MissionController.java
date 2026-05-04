package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.enums.Status;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {
    private final MissionService missionService;

    // 미션 목록 조회 (진행 중 / 완료)
    @GetMapping("/missions")
    public ApiResponse<MissionResDTO.MissionList> missionList(
            @RequestParam(name = "status") Status status,                       // basic / ongoing / done
            @RequestParam(name = "page", defaultValue = "0") Integer page,      // 0부터 시작
            @RequestParam(name = "size", defaultValue = "10") Integer size      // 기본 10
    ) {
        BaseSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.missionList(status, page, size));
    }

    // 미션 도전하기
    @PostMapping("/missions/{missionId}/challenges")
    public ApiResponse<MissionResDTO.ChallengeMission> challengeMission(
            @PathVariable Long missionId,
            @RequestBody MissionReqDTO.ChallengeMission dto
    ) {
        BaseSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.challengeMission(missionId, dto));
    }

    // 미션 성공 누르기
    @PostMapping("/user-missions/{userMissionId}/complete")
    public ApiResponse<MissionResDTO.CompleteMission> completeMission(
            @PathVariable Long userMissionId
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, missionService.completeMission(userMissionId));
    }

    // 미션 성공 인증
    @PostMapping("/missions/{missionId}/verify")
    public ApiResponse<MissionResDTO.VerifyMission> verifyMission(
            @PathVariable Long missionId,
            @RequestBody MissionReqDTO.VerifyMission dto
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, missionService.verifyMission(missionId, dto));
    }

    // 내 미션 목록 조회 (진행 중 / 완료)
    @GetMapping("/members/{memberId}/missions")
    public ApiResponse<MissionResDTO.MemberMissionList> memberMissionList(
            @PathVariable Long memberId,
            @RequestParam(name = "status") Status status,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size
    ) {
        BaseSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.memberMissionList(memberId, status, page, size));
    }
}
