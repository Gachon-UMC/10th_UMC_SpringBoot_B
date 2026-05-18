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
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 회원 도메인의 REST API를 처리하는 컨트롤러입니다.
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    // 회원 관련 비즈니스 로직을 처리하는 서비스입니다.
    private final MemberService memberService;

    // 홈 화면 미션 조회 로직을 처리하는 서비스입니다.
    private final MissionService missionService;

    // 회원가입 요청을 처리합니다.
    @PostMapping
    public ResponseEntity<ApiResponse<UserJoinResponseDTO>> joinUser(
            // 요청 본문 JSON을 회원가입 요청 DTO로 바인딩하고 검증합니다.
            @RequestBody @Valid UserJoinRequestDTO request
    ) {
        UserJoinResponseDTO response = memberService.join(request);

        return ResponseEntity.ok(ApiResponse.onSuccess(MemberSuccessCode.JOIN_SUCCESS, response));
    }

    // 홈 화면 회원 정보를 조회합니다.
    @GetMapping("/home")
    public ResponseEntity<ApiResponse<HomeResponseDTO>> home(
            // URL에 노출하지 않고 요청 헤더의 userId 값으로 회원을 식별합니다.
            @RequestHeader("userId") Long userId
    ) {
        HomeResponseDTO response = memberService.getMyPage(userId);

        return ResponseEntity.ok(ApiResponse.onSuccess(MemberSuccessCode.GET_MEMBER_SUCCESS, response));
    }

    // 마이페이지 회원 정보를 조회합니다.
    @GetMapping("/mypage")
    public ResponseEntity<ApiResponse<HomeResponseDTO>> getMyPage(
            // URL에 노출하지 않고 요청 헤더의 userId 값으로 회원을 식별합니다.
            @RequestHeader("userId") Long userId
    ) {
        HomeResponseDTO response = memberService.getMyPage(userId);

        return ResponseEntity.ok(ApiResponse.onSuccess(MemberSuccessCode.GET_MEMBER_SUCCESS, response));
    }

    // 홈 화면에서 도전 가능한 미션 목록을 조회합니다.
    @GetMapping("/missions/home")
    public ResponseEntity<ApiResponse<MissionListResDTO>> getHomeMissions(
            // 요청 쿼리의 locationId 값을 바인딩합니다.
            @RequestParam Long locationId,
            // 요청 쿼리의 page 값을 바인딩하고 기본값을 지정합니다.
            @RequestParam(defaultValue = "0") int page,
            // 요청 쿼리의 size 값을 바인딩하고 기본값을 지정합니다.
            @RequestParam(defaultValue = "10") int size
    ) {
        MissionListResDTO response =
                missionService.getHomeMissions(locationId, page, size);

        return ResponseEntity.ok(ApiResponse.onSuccess(MissionSuccessCode.GET_MISSIONS_SUCCESS, response));
    }
}
