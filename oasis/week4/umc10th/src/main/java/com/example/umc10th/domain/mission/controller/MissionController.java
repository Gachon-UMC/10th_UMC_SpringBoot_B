package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/{userId}/missions")
public class MissionController {

    @GetMapping
    public ResponseEntity<ApiResponse<MissionResDTO.MissionListResponse>> getMissions(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "progress") String status
    ) {
        MissionResDTO.MissionListResponse response = null;
        return ResponseEntity
                .status(MissionSuccessCode.MISSION_LIST_SUCCESS.getStatus())
                .body(ApiResponse.onSuccess(MissionSuccessCode.MISSION_LIST_SUCCESS, response));
    }

    @PatchMapping("/{missionId}/complete")
    public ResponseEntity<ApiResponse<MissionResDTO.MissionCompleteResponse>> completeMission(
            @PathVariable Long userId,
            @PathVariable Long missionId
    ) {
        MissionResDTO.MissionCompleteResponse response = null;
        return ResponseEntity
                .status(MissionSuccessCode.MISSION_COMPLETE_SUCCESS.getStatus())
                .body(ApiResponse.onSuccess(MissionSuccessCode.MISSION_COMPLETE_SUCCESS, response));
    }
}
