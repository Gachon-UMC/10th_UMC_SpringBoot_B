package com.example.umc10th.domain.auth.controller;

import com.example.umc10th.domain.auth.dto.AuthReqDTO;
import com.example.umc10th.domain.auth.dto.AuthResDTO;
import com.example.umc10th.domain.auth.exception.code.AuthSuccessCode;
import com.example.umc10th.domain.auth.service.AuthService;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    /**
     * 회원가입
     */
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<AuthResDTO.Register> register(
            @RequestBody @Valid AuthReqDTO.Register dto
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.CREATED, authService.register(dto));
    }

    // 로그인
    @PostMapping("/login")
    public ApiResponse<AuthResDTO.Login> login(
            @RequestBody @Valid AuthReqDTO.Login dto
    ) {
        return ApiResponse.onSuccess(AuthSuccessCode.LOGIN_SUCCESS, authService.login(dto));
    }

    // 로그아웃
    @PostMapping("/logout")
    public ApiResponse<AuthResDTO.LogoutResult> logout(
            @Parameter(hidden = true)
            @RequestHeader("Authorization") String accessToken
    ) {
        return ApiResponse.onSuccess(AuthSuccessCode.LOGOUT_SUCCESS, authService.logout(accessToken));
    }
}
