package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.HomeResDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/{userId}")
public class HomeController {

    @GetMapping("/home")
    public ResponseEntity<ApiResponse<HomeResDTO.HomeResponse>> home(
            @PathVariable Long userId
    ) {
        HomeResDTO.HomeResponse response = null;
        return ResponseEntity
                .status(MemberSuccessCode.MEMBER_HOME_SUCCESS.getStatus())
                .body(ApiResponse.onSuccess(MemberSuccessCode.MEMBER_HOME_SUCCESS, response));
    }

    @GetMapping("/mypage")
    public ResponseEntity<ApiResponse<MemberResDTO.MypageResponse>> mypage(
            @PathVariable Long userId
    ) {
        MemberResDTO.MypageResponse response = null;
        return ResponseEntity
                .status(MemberSuccessCode.MEMBER_MYPAGE_SUCCESS.getStatus())
                .body(ApiResponse.onSuccess(MemberSuccessCode.MEMBER_MYPAGE_SUCCESS, response));
    }
}
