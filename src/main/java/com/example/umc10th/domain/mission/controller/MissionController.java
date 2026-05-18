package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionChallengeResDTO;
import com.example.umc10th.domain.mission.dto.MissionCompleteResDTO;
import com.example.umc10th.domain.mission.dto.MissionListResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 미션 도메인의 REST API를 처리하는 컨트롤러입니다.
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class MissionController {

    // 미션 관련 비즈니스 로직을 처리하는 서비스입니다.
    private final MissionService missionService;

    // 사용자의 미션 목록을 상태별로 조회합니다.
    @GetMapping("/missions")
    public ResponseEntity<ApiResponse<MissionListResDTO>> getMissions(
            // URL에 노출하지 않고 요청 헤더의 userId 값으로 회원을 식별합니다.
            @RequestHeader("userId") Long userId,
            // all, in-progress, completed 값으로 미션 상태를 필터링합니다.
            @RequestParam(defaultValue = "all") String status,
            // 요청 쿼리의 page 값을 바인딩하고 기본값을 지정합니다.
            @RequestParam(defaultValue = "0") int page,
            // 요청 쿼리의 size 값을 바인딩하고 기본값을 지정합니다.
            @RequestParam(defaultValue = "10") int size
    ) {
        MissionListResDTO response =
                missionService.getMyMissions(userId, status, page, size);

        return ResponseEntity.ok(ApiResponse.onSuccess(MissionSuccessCode.GET_MISSIONS_SUCCESS, response));
    }

    // 사용자가 특정 미션에 도전합니다.
    @PostMapping("/missions/{missionId}/challenge")
    public ResponseEntity<ApiResponse<MissionChallengeResDTO>> challengeMission(
            // URL에 노출하지 않고 요청 헤더의 userId 값으로 회원을 식별합니다.
            @RequestHeader("userId") Long userId,
            // URL 경로의 missionId 값을 메서드 파라미터로 바인딩합니다.
            @PathVariable Long missionId
    ) {
        MissionChallengeResDTO response =
                missionService.challengeMission(userId, missionId);

        return ResponseEntity.ok(ApiResponse.onSuccess(MissionSuccessCode.CHALLENGE_MISSION_SUCCESS, response));
    }

    // 사용자의 미션 성공 인증을 처리합니다.
    @PatchMapping("/missions/{userMissionId}/complete")
    public ResponseEntity<ApiResponse<MissionCompleteResDTO>> completeMission(
            // URL에 노출하지 않고 요청 헤더의 userId 값으로 회원을 식별합니다.
            @RequestHeader("userId") Long userId,
            // URL 경로의 userMissionId 값을 메서드 파라미터로 바인딩합니다.
            @PathVariable Long userMissionId
    ) {
        MissionCompleteResDTO response =
                missionService.completeMission(userId, userMissionId);

        return ResponseEntity.ok(ApiResponse.onSuccess(MissionSuccessCode.COMPLETE_MISSION_SUCCESS, response));
    }
}
