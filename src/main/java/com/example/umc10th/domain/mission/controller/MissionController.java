package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionChallengeResDTO;
import com.example.umc10th.domain.mission.dto.MissionCompleteResDTO;
import com.example.umc10th.domain.mission.dto.MissionListResDTO;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// 해당 클래스를 REST API 컨트롤러로 등록합니다.
@RestController
// final 필드를 생성자 주입 방식으로 주입합니다.
@RequiredArgsConstructor
// 사용자 미션 API의 공통 URL 경로를 지정합니다.
@RequestMapping("/api/users")
public class MissionController {

    // 미션 관련 비즈니스 로직을 처리하는 서비스입니다.
    private final MissionService missionService;

    // Request Body의 사용자 ID 기준으로 진행중인 미션 목록을 조회합니다.
    @PostMapping("/missions/in-progress")
    public ResponseEntity<ApiResponse<MissionResDTO.Pagination<MissionResDTO.GetMission>>> getInProgressMissions(
            // 요청 본문 JSON을 진행중인 미션 조회 요청 DTO로 바인딩합니다.
            @RequestBody @Valid MissionReqDTO.InProgressMissionListDTO request
    ) {
        MissionResDTO.Pagination<MissionResDTO.GetMission> response =
                missionService.getInProgressMissions(request);

        return ResponseEntity.ok(ApiResponse.onSuccess(MissionSuccessCode.GET_IN_PROGRESS_MISSIONS_SUCCESS, response));
    }

    // 사용자의 미션 목록을 조회합니다.
    @GetMapping("/{userId}/missions")
    public ResponseEntity<ApiResponse<MissionListResDTO>> getMissions(
            // URL 경로의 userId 값을 메서드 파라미터로 바인딩합니다.
            @PathVariable Long userId,
            // 요청 쿼리의 page 값을 바인딩하고 기본값을 지정합니다.
            @RequestParam(defaultValue = "0") int page,
            // 요청 쿼리의 size 값을 바인딩하고 기본값을 지정합니다.
            @RequestParam(defaultValue = "10") int size
    ) {
        MissionListResDTO response =
                missionService.getMyMissions(userId, page, size);

        return ResponseEntity.ok(ApiResponse.onSuccess(MissionSuccessCode.GET_MISSIONS_SUCCESS, response));
    }

    // 사용자가 특정 미션에 도전합니다.
    @PostMapping("/{userId}/missions/{missionId}/challenge")
    public ResponseEntity<ApiResponse<MissionChallengeResDTO>> challengeMission(
            // URL 경로의 userId 값을 메서드 파라미터로 바인딩합니다.
            @PathVariable Long userId,
            // URL 경로의 missionId 값을 메서드 파라미터로 바인딩합니다.
            @PathVariable Long missionId
    ) {
        MissionChallengeResDTO response =
                missionService.challengeMission(userId, missionId);

        return ResponseEntity.ok(ApiResponse.onSuccess(MissionSuccessCode.CHALLENGE_MISSION_SUCCESS, response));
    }

    // 사용자의 미션 성공 인증을 처리합니다.
    @PatchMapping("/{userId}/missions/{userMissionId}/complete")
    public ResponseEntity<ApiResponse<MissionCompleteResDTO>> completeMission(
            // URL 경로의 userId 값을 메서드 파라미터로 바인딩합니다.
            @PathVariable Long userId,
            // URL 경로의 userMissionId 값을 메서드 파라미터로 바인딩합니다.
            @PathVariable Long userMissionId
    ) {
        MissionCompleteResDTO response =
                missionService.completeMission(userId, userMissionId);

        return ResponseEntity.ok(ApiResponse.onSuccess(MissionSuccessCode.COMPLETE_MISSION_SUCCESS, response));
    }
}
