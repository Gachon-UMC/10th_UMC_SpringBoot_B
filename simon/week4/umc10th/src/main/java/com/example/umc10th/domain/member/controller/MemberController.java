package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;
    // Query Parameter
    @PostMapping("/query-parameter")
    public ApiResponse<String> exception(
            @RequestParam String queryParameter
    ) {
        BaseSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, memberService.singleParameter(queryParameter));
    }

    // Path Variable
    @PostMapping("/{pathVariable}")
    public String pathVariable(
            @PathVariable String pathVariable
    ) {
        return memberService.singleParameter(pathVariable);
    }

    // Header
    @PostMapping("/header")
    public String header(
            @RequestHeader("test") String test
    ) {
        return memberService.singleParameter(test);
    }

    // 마이페이지
    @GetMapping("/me")
    public ApiResponse<MemberResDTO.GetInfo> getInfo(
            @ModelAttribute MemberReqDTO.GetInfo dto
    ) {
        BaseSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, memberService.getInfo(dto));
    }

    // 회원정보 수정
    @PatchMapping("/me")
    public ApiResponse<MemberResDTO.UpdateInfo> updateInfo(
            @RequestBody MemberReqDTO.UpdateInfo dto
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, memberService.updateInfo(dto));
    }

    // 회원 탈퇴
    @DeleteMapping("/me")
    public ApiResponse<String> deleteMember() {
        memberService.deleteMember();
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, "탈퇴가 완료되었습니다.");
    }
}
