package org.example.umc10thyongjae.domain.auth.controller;

import org.example.umc10thyongjae.domain.auth.dto.SignUpRequestDto;
import org.example.umc10thyongjae.global.apiPayload.ApiResponse;
import org.example.umc10thyongjae.global.apiPayload.code.GeneralSuccessCode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @PostMapping("/signUp")
    public ApiResponse<Void> signUp(
            @RequestBody SignUpRequestDto dto
            ) {

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);

    }
}
