package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {
    private final MemberService memberService;

    // 마이페이지
    @GetMapping("/me")
    public ApiResponse<MemberResDTO.GetInfo> getInfo(
            @RequestParam Long memberId
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.OK, memberService.getInfo(memberId));
    }

    // 회원정보 수정
    @PatchMapping("/me/profile")
    public ApiResponse<MemberResDTO.UpdateInfo> updateInfo(
            @RequestParam Long memberId,
            @RequestBody @Valid MemberReqDTO.UpdateInfo dto
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.UPDATED, memberService.updateInfo(memberId, dto));
    }

    // 탈퇴
    @DeleteMapping("/me/withdraw")
    public ApiResponse<String> deleteMember(@RequestParam Long memberId) {
        memberService.deleteMember(memberId);
        return ApiResponse.onSuccess(MemberSuccessCode.DELETED, "탈퇴가 완료되었습니다.");
    }
}
