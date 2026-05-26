package com.example.umc10th.domain.auth.controller;

import com.example.umc10th.domain.auth.dto.AuthReqDto;
import com.example.umc10th.domain.auth.dto.AuthResDto;
import com.example.umc10th.domain.auth.service.AuthService;
import com.example.umc10th.domain.user.exception.code.UserSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ApiResponse<AuthResDto.SignupResult> singup(
            @RequestBody AuthReqDto.Signup signup
    ){
        AuthResDto.SignupResult signupResult = authService.signup(signup);
        BaseSuccessCode code = UserSuccessCode.OK;
        return ApiResponse.onSuccess(code, signupResult);
    }
}
