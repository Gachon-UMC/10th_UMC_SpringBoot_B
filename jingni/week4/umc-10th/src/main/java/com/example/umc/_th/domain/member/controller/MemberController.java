package com.example.umc._th.domain.member.controller;

import com.example.umc._th.domain.member.dto.MemberReqDTO;
import com.example.umc._th.domain.member.dto.MemberResDTO;
import com.example.umc._th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc._th.domain.member.service.MemberService;
import com.example.umc._th.global.apiPayload.ApiResponse;
import com.example.umc._th.global.apiPayload.code.BaseSuccessCode;
import com.example.umc._th.global.security.entity.AuthMember;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("v1/members/signup")
    public ApiResponse<MemberResDTO.Signup> signup (
            @RequestBody @Valid MemberReqDTO.Signup dto
            ){

        BaseSuccessCode code = MemberSuccessCode.CREATED;
        return ApiResponse.onSuccess(code, memberService.signup(dto));
    }

    @GetMapping("/v1/members/me")
    public ApiResponse<MemberResDTO.GetInfo> getInfo (
            @AuthenticationPrincipal AuthMember member
            ){
        BaseSuccessCode code = MemberSuccessCode.OK;
        return ApiResponse.onSuccess(code, memberService.getInfo(member.getMember().getId()));
    }

    @PostMapping("v1/members/login")
    public ApiResponse<MemberResDTO.Login> login(
            @RequestBody @Valid MemberReqDTO.Login dto
    ) {
        BaseSuccessCode code = MemberSuccessCode.OK;
        return ApiResponse.onSuccess(code, memberService.login(dto));
    }
}
