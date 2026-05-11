package com.example.umc10th.domain.user.controller;

import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.exception.code.UserSuccessCode;
import com.example.umc10th.domain.user.service.UserService;
import com.example.umc10th.domain.user.service.UserServiceImpl;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class UserController {

    private final UserService userService;

    //마이페이지
    @PostMapping("/users/{userId}")
    public ApiResponse<UserResDTO.GetInfo> getInfo(
            @PathVariable Long userId
    ){
        BaseSuccessCode code = UserSuccessCode.OK;
        return ApiResponse.onSuccess(code, userService.getInfo(userId));
    }

}
