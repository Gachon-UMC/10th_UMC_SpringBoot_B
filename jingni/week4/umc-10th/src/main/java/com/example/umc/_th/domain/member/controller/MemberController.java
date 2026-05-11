package com.example.umc._th.domain.member.controller;

import com.example.umc._th.domain.member.dto.MemberReqDTO;
import com.example.umc._th.domain.member.dto.MemberResDTO;
import com.example.umc._th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc._th.domain.member.service.MemberService;
import com.example.umc._th.global.apiPayload.ApiResponse;
import com.example.umc._th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("v1/members/signup")
    public ApiResponse<MemberResDTO.Signup> signup (
            @RequestBody MemberReqDTO.Signup dto
            ){
        BaseSuccessCode code = MemberSuccessCode.CREATED;
        return ApiResponse.onSuccess(code, memberService.signup(dto));
    }

    @GetMapping("/v1/members/me")
    public ApiResponse<MemberResDTO.GetInfo> getInfo (){
        BaseSuccessCode code = MemberSuccessCode.OK;
        // 일단은 userId를 1로 고정 (추후 JWT 토큰에서 꺼내도록 구현)
        Long memberId = 100L;
        return ApiResponse.onSuccess(code, memberService.getInfo(memberId));
    }
}
