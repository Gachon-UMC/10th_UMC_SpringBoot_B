package org.example.umc10thyongjae.domain.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.umc10thyongjae.domain.auth.dto.request.SignUpRequestDto;
import org.example.umc10thyongjae.domain.auth.dto.response.UserInfoResponseDto;
import org.example.umc10thyongjae.domain.auth.service.AuthService;
import org.example.umc10thyongjae.global.apiPayload.ApiResponse;
import org.example.umc10thyongjae.global.apiPayload.code.GeneralSuccessCode;
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
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);

    }

    @GetMapping("/users/me")
    public ApiResponse<UserInfoResponseDto> retrieveUserInfo(
            @RequestAttribute long userId
    ) {
        UserInfoResponseDto result = authService.getUserInfo(userId);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}
