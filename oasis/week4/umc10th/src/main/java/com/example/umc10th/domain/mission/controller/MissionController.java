package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.api.MissionApiSpecification;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController implements MissionApiSpecification {

    private final MissionService missionService;

    @GetMapping("/users/{userId}/missions")
    public ResponseEntity<ApiResponse<MissionResDTO.MissionListResponse>> getMissions(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "progress") String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        MissionResDTO.MissionListResponse response = missionService.getMissions(userId, status, page, size);
        return ResponseEntity
                .status(MissionSuccessCode.MISSION_LIST_SUCCESS.getStatus())
                .body(ApiResponse.onSuccess(MissionSuccessCode.MISSION_LIST_SUCCESS, response));
    }

    @PatchMapping("/users/{userId}/missions/{missionId}/complete")
    public ResponseEntity<ApiResponse<MissionResDTO.MissionCompleteResponse>> completeMission(
            @PathVariable Long userId,
            @PathVariable Long missionId
    ) {
        MissionResDTO.MissionCompleteResponse response = missionService.completeMission(userId, missionId);
        return ResponseEntity
                .status(MissionSuccessCode.MISSION_COMPLETE_SUCCESS.getStatus())
                .body(ApiResponse.onSuccess(MissionSuccessCode.MISSION_COMPLETE_SUCCESS, response));
    }

    @PostMapping("/missions/my")
    public ResponseEntity<ApiResponse<MissionResDTO.MissionListResponse>> getMyMissions(
            @Valid @RequestBody MissionReqDTO.MyMissionRequest request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        MissionResDTO.MissionListResponse response = missionService.getMissions(
                request.memberId(), "progress", page, size);
        return ResponseEntity
                .status(MissionSuccessCode.MY_MISSION_LIST_SUCCESS.getStatus())
                .body(ApiResponse.onSuccess(MissionSuccessCode.MY_MISSION_LIST_SUCCESS, response));
    }
}
