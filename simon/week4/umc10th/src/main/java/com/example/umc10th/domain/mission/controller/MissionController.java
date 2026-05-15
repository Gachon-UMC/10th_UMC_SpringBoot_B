package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.domain.store.enums.RegionName;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MissionController {
    private final MissionService missionService;

    // 특정 가게의 미션들 조회
    @GetMapping("/stores/{storeId}/missions")
    public ApiResponse<MissionResDTO.Pagination<MissionResDTO.GetMission>> getMissions(
            @PathVariable Long storeId,
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber,
            @RequestParam(required = false) String sort
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.OK, missionService.getMissions(storeId, pageSize, pageNumber, sort));
    }

    // 미션 도전하기
    @PostMapping("/missions/{missionId}/challenges")
    public ApiResponse<MissionResDTO.ChallengeMission> challengeMission(
            @PathVariable Long missionId,
            @RequestBody @Valid MissionReqDTO.ChallengeMission dto
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_STARTED, missionService.challengeMission(missionId, dto));
    }

    // 미션 성공 누르기
    @PostMapping("/member-missions/{memberMissionId}/complete")
    public ApiResponse<MissionResDTO.CompleteMission> completeMission(
            @PathVariable Long memberMissionId
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_VERIFIED, missionService.completeMission(memberMissionId));
    }

    // 미션 성공 인증
    @PostMapping("/missions/{missionId}/verify")
    public ApiResponse<MissionResDTO.VerifyMission> verifyMission(
            @PathVariable Long missionId,
            @RequestBody @Valid MissionReqDTO.VerifyMission dto
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_COMPLETED, missionService.verifyMission(missionId, dto));
    }

    // 가게 미션 생성
    @PostMapping("/stores/{storeId}/missions")
    public ApiResponse<Void> createMission(
            @PathVariable Long storeId,
            @RequestBody @Valid MissionReqDTO.CreateMission dto
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.CREATED, missionService.createMission(storeId, dto));
    }

    // 홈 화면 미션 목록 조회
    @GetMapping("/home")
    public ApiResponse<MissionResDTO.HomeMissionResponse> homeMissionList(
            @RequestParam(name = "memberId") Long memberId,
            @RequestParam(name = "regionName") RegionName regionName,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String sort
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.OK,
                missionService.homeMissionList(memberId, regionName, page, size, sort));
    }
}
