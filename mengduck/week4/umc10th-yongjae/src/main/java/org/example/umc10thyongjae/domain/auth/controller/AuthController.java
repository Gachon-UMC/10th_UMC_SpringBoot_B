package org.example.umc10thyongjae.domain.auth.controller;

import org.example.umc10thyongjae.domain.auth.dto.request.SignUpRequestDto;
import org.example.umc10thyongjae.domain.auth.dto.response.UserInfoResponseDto;
import org.example.umc10thyongjae.global.apiPayload.ApiResponse;
import org.example.umc10thyongjae.global.apiPayload.code.GeneralSuccessCode;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @PostMapping("/signUp")
    public ApiResponse<Void> signUp(
            @RequestBody SignUpRequestDto dto
    ) {

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);

    }

    @GetMapping("/users/me")
    public ApiResponse<UserInfoResponseDto> retrieveUserInfo(
            @RequestAttribute int userKey
    ) {
        UserInfoResponseDto result = UserInfoResponseDto.builder()
                .userKey(userKey)
                .name("용재")
                .mail("yj@yj.kr")
                .phoneNumber("010-1111-2222")
                .phoneNumberVerified(false)
                .point(5000)
                .build();

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}
