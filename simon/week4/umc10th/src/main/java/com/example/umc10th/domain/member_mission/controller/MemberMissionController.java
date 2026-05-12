package com.example.umc10th.domain.member_mission.controller;

import com.example.umc10th.domain.member_mission.converter.MemberMissionConverter;
import com.example.umc10th.domain.member_mission.dto.MemberMissionReqDTO;
import com.example.umc10th.domain.member_mission.service.MemberMissionService;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MemberMissionController {
    private final MemberMissionService memberMissionService;

    // 나의 미션 목록 조회 (진행 중 / 완료)
    @PostMapping("/members/missions")
    public ApiResponse<MissionResDTO.Pagination<MissionResDTO.MemberMissionItem>> memberMissionList(
            @RequestBody @Valid MemberMissionReqDTO.MyMissionRequest dto
    ) {
        var result = memberMissionService.getMemberMissionList(dto);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, MemberMissionConverter.toMemberMissionPagination(result));
    }
}
