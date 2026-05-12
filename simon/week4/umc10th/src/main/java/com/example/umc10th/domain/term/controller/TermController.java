package com.example.umc10th.domain.term.controller;

import com.example.umc10th.domain.term.converter.TermConverter;
import com.example.umc10th.domain.term.dto.TermResDTO;
import com.example.umc10th.domain.term.entity.MemberTerm;
import com.example.umc10th.domain.term.exception.code.TermSuccessCode;
import com.example.umc10th.domain.term.service.TermService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TermController {
    private final TermService termService;

    // 특정 사용자의 약관 동의 내역 조회
    @GetMapping("/members/{memberId}/terms")
    public ApiResponse<TermResDTO.MemberTermList> getMemberTerms(
            @PathVariable Long memberId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<MemberTerm> pageResult = termService.getMemberTerms(memberId, page, size);

        return ApiResponse.onSuccess(TermSuccessCode.TERM_LIST_FETCHED, TermConverter.toMemberTermListDTO(pageResult));
    }
}
