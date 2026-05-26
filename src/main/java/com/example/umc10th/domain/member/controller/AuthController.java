package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.UserLoginRequestDTO;
import com.example.umc10th.domain.member.dto.UserLoginResponseDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 인증 관련 REST API를 처리하는 컨트롤러입니다.
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final MemberService memberService;

    // 로그인 요청을 처리하고 JWT 토큰을 발급합니다.
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserLoginResponseDTO>> login(
            @RequestBody @Valid UserLoginRequestDTO request
    ) {
        UserLoginResponseDTO response = memberService.login(request);

        return ResponseEntity.ok(ApiResponse.onSuccess(MemberSuccessCode.LOGIN_SUCCESS, response));
    }
}
