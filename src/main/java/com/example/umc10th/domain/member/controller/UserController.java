package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.HomeResponseDTO;
import com.example.umc10th.domain.member.dto.UserJoinRequestDTO;
import com.example.umc10th.domain.member.dto.UserJoinResponseDTO;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.domain.mission.dto.MissionListResDTO;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final MemberService memberService;
    private final MissionService missionService;

    // 회원가입
    @PostMapping
    public ApiResponse<UserJoinResponseDTO> joinUser(
            @RequestBody UserJoinRequestDTO request
    ) {
        UserJoinResponseDTO response = new UserJoinResponseDTO(1L);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }

    // 홈 조회
    @GetMapping("/{userId}/home")
    public ApiResponse<HomeResponseDTO> home(
            @PathVariable Long userId
    ) {
        HomeResponseDTO response = new HomeResponseDTO(
                "링크",
                "test@test.com",
                "010-0000-0000",
                1000
        );;

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }

    // 마이 페이지
    @GetMapping("/{userId}/mypage")
    public ApiResponse<HomeResponseDTO> getMyPage(
            @PathVariable Long userId
    ) {

        HomeResponseDTO response = memberService.getMyPage(userId);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }

    // 홈 화면 미션 조회 커서 페이징
    @GetMapping("/missions/home")
    public ApiResponse<MissionListResDTO> getHomeMissions(
            @RequestParam Long locationId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        MissionListResDTO response =
                missionService.getHomeMissions(locationId, page, size);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }
}