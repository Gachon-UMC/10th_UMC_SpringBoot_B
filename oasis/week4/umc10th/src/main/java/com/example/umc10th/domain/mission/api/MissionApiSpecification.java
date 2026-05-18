package com.example.umc10th.domain.mission.api;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Mission", description = "미션 관련 API")
public interface MissionApiSpecification {

    @Operation(summary = "내 미션 목록 조회", description = "진행중/진행완료 상태별 미션 목록을 페이징하여 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "미션 목록 조회 성공"),
            @ApiResponse(responseCode = "404", description = "회원을 찾을 수 없음")
    })
    ResponseEntity<com.example.umc10th.global.apiPayload.ApiResponse<MissionResDTO.MissionListResponse>> getMissions(
            @Parameter(description = "사용자 ID", example = "1") @PathVariable Long userId,
            @Parameter(description = "미션 상태 (progress/complete)", example = "progress") @RequestParam(defaultValue = "progress") String status,
            @Parameter(description = "페이지 번호 (0부터 시작)", example = "0") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "페이지 크기", example = "10") @RequestParam(defaultValue = "10") int size
    );

    @Operation(summary = "미션 진행 완료", description = "진행 중인 미션을 완료 처리합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "미션 완료 성공"),
            @ApiResponse(responseCode = "400", description = "이미 완료된 미션"),
            @ApiResponse(responseCode = "404", description = "회원 미션을 찾을 수 없음")
    })
    ResponseEntity<com.example.umc10th.global.apiPayload.ApiResponse<MissionResDTO.MissionCompleteResponse>> completeMission(
            @Parameter(description = "사용자 ID", example = "1") @PathVariable Long userId,
            @Parameter(description = "미션 ID", example = "1") @PathVariable Long missionId
    );

    @Operation(summary = "내 진행중인 미션 조회", description = "Request Body로 사용자 ID를 받아 진행중인 미션 목록을 오프셋 기반 페이지네이션으로 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "내 진행중인 미션 조회 성공"),
            @ApiResponse(responseCode = "404", description = "회원을 찾을 수 없음")
    })
    ResponseEntity<com.example.umc10th.global.apiPayload.ApiResponse<MissionResDTO.MissionListResponse>> getMyMissions(
            @RequestBody MissionReqDTO.MyMissionRequest request,
            @Parameter(description = "페이지 번호 (0부터 시작)", example = "0") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "페이지 크기", example = "10") @RequestParam(defaultValue = "10") int size
    );
}
