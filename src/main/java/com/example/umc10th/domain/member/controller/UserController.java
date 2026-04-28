package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.HomeResponseDTO;
import com.example.umc10th.domain.member.dto.ReviewRequestDTO;
import com.example.umc10th.domain.member.dto.UserJoinRequestDTO;
import com.example.umc10th.domain.member.dto.UserJoinResponseDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

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

        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                new HomeResponseDTO("홈 화면입니다.")
        );
    }

    // 리뷰 작성
    @PostMapping("/{userId}/reviews")
    public ApiResponse<String> writeReview(
            @PathVariable Long userId,
            @RequestBody ReviewRequestDTO request
    ) {

        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                "리뷰 작성 완료"
        );
    }

    // 미션 목록 조회
    @GetMapping("/{userId}/missions")
    public ApiResponse<String> getMissions(
            @PathVariable Long userId,
            @RequestParam String status,
            @RequestParam int cursor,
            @RequestParam int size
    ) {

        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                "미션 목록 조회"
        );
    }

    // 미션 완료
    @PatchMapping("/{userId}/missions/{userMissionId}/complete")
    public ApiResponse<String> completeMission(
            @PathVariable Long userId,
            @PathVariable Long userMissionId
    ) {

        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                "미션 완료"
        );
    }
}