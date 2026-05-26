package org.example.umc10thyongjae.domain.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.umc10thyongjae.domain.auth.dto.request.LoginRequestDto;
import org.example.umc10thyongjae.domain.auth.dto.request.SignUpRequestDto;
import org.example.umc10thyongjae.domain.auth.dto.response.LoginResponseDto;
import org.example.umc10thyongjae.domain.auth.dto.response.SignUpResponseDto;
import org.example.umc10thyongjae.domain.auth.dto.response.UserInfoResponseDto;
import org.example.umc10thyongjae.domain.auth.service.AuthService;
import org.example.umc10thyongjae.global.apiPayload.ApiResponse;
import org.example.umc10thyongjae.global.apiPayload.code.GeneralSuccessCode;
import org.example.umc10thyongjae.global.security.entity.AuthUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signUp")
    public ApiResponse<Void> signUp(
            @RequestBody @Valid SignUpRequestDto dto
    ) {
        Long userId = authService.signUp(dto);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);
    }

    @PostMapping("/login")
    public ApiResponse<LoginResponseDto> login(
            @RequestBody @Valid LoginRequestDto dto
    ) {
        LoginResponseDto result = authService.login(dto);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    @GetMapping("/users/me")
    public ApiResponse<UserInfoResponseDto> retrieveUserInfo(
            @AuthenticationPrincipal AuthUser authUser
            ) {
        long userId = authUser.getUser().getId();
        UserInfoResponseDto result = authService.getUserInfo(userId);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}
