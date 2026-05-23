package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.security.entity.AuthMember;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {
    private final MemberService memberService;

    // 마이페이지
    @GetMapping("/me")
    public ApiResponse<MemberResDTO.GetInfo> getInfo(
            @AuthenticationPrincipal AuthMember authMember
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.OK, memberService.getInfo(authMember.getMember()));
    }

    // 회원정보 수정
    @PatchMapping("/me/profile")
    public ApiResponse<MemberResDTO.UpdateInfo> updateInfo(
            @AuthenticationPrincipal AuthMember authMember,
            @RequestBody @Valid MemberReqDTO.UpdateInfo dto
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.UPDATED, memberService.updateInfo(authMember.getMember(), dto));
    }

    // 탈퇴
    @DeleteMapping("/me/withdraw")
    public ApiResponse<String> deleteMember(
            @AuthenticationPrincipal AuthMember authMember
    ) {
        memberService.deleteMember(authMember.getMember());
        return ApiResponse.onSuccess(MemberSuccessCode.DELETED, "탈퇴가 완료되었습니다.");
    }
}
