package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.HomeResponseDTO;
import com.example.umc10th.domain.member.dto.UserJoinRequestDTO;
import com.example.umc10th.domain.member.dto.UserJoinResponseDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.domain.mission.dto.MissionListResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.security.AuthMember;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 회원 도메인의 REST API를 처리하는 컨트롤러입니다.
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final MemberService memberService;
    private final MissionService missionService;

    // 회원가입 요청을 처리합니다.
    @PostMapping
    public ResponseEntity<ApiResponse<UserJoinResponseDTO>> joinUser(
            @RequestBody @Valid UserJoinRequestDTO request
    ) {
        UserJoinResponseDTO response = memberService.join(request);

        return ResponseEntity.ok(ApiResponse.onSuccess(MemberSuccessCode.JOIN_SUCCESS, response));
    }

    // JWT 토큰 기반 홈 화면 회원 정보를 조회합니다.
    @GetMapping("/home")
    public ResponseEntity<ApiResponse<HomeResponseDTO>> home(
            @AuthenticationPrincipal AuthMember authMember
    ) {
        HomeResponseDTO response = memberService.getMyPage(authMember);

        return ResponseEntity.ok(ApiResponse.onSuccess(MemberSuccessCode.GET_MEMBER_SUCCESS, response));
    }

    // JWT 토큰 기반 마이페이지 정보를 조회합니다.
    @GetMapping("/mypage")
    public ResponseEntity<ApiResponse<HomeResponseDTO>> getMyPage(
            @AuthenticationPrincipal AuthMember authMember
    ) {
        HomeResponseDTO response = memberService.getMyPage(authMember);

        return ResponseEntity.ok(ApiResponse.onSuccess(MemberSuccessCode.GET_MEMBER_SUCCESS, response));
    }

    // 워크북 형식의 JWT 토큰 기반 마이페이지 정보를 조회합니다.
    @GetMapping("/me")
    public ResponseEntity<ApiResponse<HomeResponseDTO>> getMe(
            @AuthenticationPrincipal AuthMember authMember
    ) {
        HomeResponseDTO response = memberService.getMyPage(authMember);

        return ResponseEntity.ok(ApiResponse.onSuccess(MemberSuccessCode.GET_MEMBER_SUCCESS, response));
    }

    // 홈 화면에서 도전 가능한 미션 목록을 조회합니다.
    @GetMapping("/missions/home")
    public ResponseEntity<ApiResponse<MissionListResDTO>> getHomeMissions(
            @RequestParam Long locationId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        MissionListResDTO response =
                missionService.getHomeMissions(locationId, page, size);

        return ResponseEntity.ok(ApiResponse.onSuccess(MissionSuccessCode.GET_MISSIONS_SUCCESS, response));
    }
}
