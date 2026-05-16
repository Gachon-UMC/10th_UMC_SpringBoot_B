package com.example.umc10th.domain.member_mission.controller;

import com.example.umc10th.domain.member_mission.converter.MemberMissionConverter;
import com.example.umc10th.domain.member_mission.enums.MissionStatus;
import com.example.umc10th.domain.member_mission.service.MemberMissionService;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.dto.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MemberMissionController {
    private final MemberMissionService memberMissionService;

    // 나의 미션 목록 조회 (진행 중 / 완료)
    @GetMapping("/members/me/missions")
    public ApiResponse<PageResponse<MissionResDTO.MemberMissionItem>> getMyMissionList(
            @RequestParam Long memberId,
            @RequestParam MissionStatus status,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        var result = memberMissionService.getMemberMissionList(memberId, status, pageable);
        return ApiResponse.onSuccess(MissionSuccessCode.OK, MemberMissionConverter.toMemberMissionPagination(result));
    }
}
