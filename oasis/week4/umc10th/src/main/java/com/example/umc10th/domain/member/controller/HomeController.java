package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.api.HomeApiSpecification;
import com.example.umc10th.domain.member.dto.HomeResDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/{userId}")
public class HomeController implements HomeApiSpecification {

    private final MemberService memberService;

    @GetMapping("/home")
    public ResponseEntity<ApiResponse<HomeResDTO.HomeResponse>> home(
            @PathVariable Long userId,
            @RequestParam Long regionId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        HomeResDTO.HomeResponse response = memberService.getHome(userId, regionId, page, size);
        return ResponseEntity
                .status(MemberSuccessCode.MEMBER_HOME_SUCCESS.getStatus())
                .body(ApiResponse.onSuccess(MemberSuccessCode.MEMBER_HOME_SUCCESS, response));
    }

    @GetMapping("/mypage")
    public ResponseEntity<ApiResponse<MemberResDTO.MypageResponse>> mypage(
            @PathVariable Long userId
    ) {
        MemberResDTO.MypageResponse response = memberService.getMypage(userId);
        return ResponseEntity
                .status(MemberSuccessCode.MEMBER_MYPAGE_SUCCESS.getStatus())
                .body(ApiResponse.onSuccess(MemberSuccessCode.MEMBER_MYPAGE_SUCCESS, response));
    }
}
