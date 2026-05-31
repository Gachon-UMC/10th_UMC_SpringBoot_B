package com.example.umc10th.domain.user.controller;

import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.exception.code.UserSuccessCode;
import com.example.umc10th.domain.user.service.UserService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import com.example.umc10th.global.security.entity.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class UserController {

    private final UserService userService;

    //마이페이지
    @PostMapping("/users/me")
    public ApiResponse<UserResDTO.GetInfo> getInfo(
            @AuthenticationPrincipal AuthMember authMember
            ){
        BaseSuccessCode code = UserSuccessCode.OK;

        Long userId = authMember.getUser().getId();
        return ApiResponse.onSuccess(code, userService.getInfo(userId));
    }

}
