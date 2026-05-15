package com.example.umc10th.domain.term.controller;

import com.example.umc10th.domain.term.dto.TermResDTO;
import com.example.umc10th.domain.term.exception.code.TermSuccessCode;
import com.example.umc10th.domain.term.service.TermService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.dto.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TermController {
    private final TermService termService;

    // 특정 사용자의 약관 동의 내역 조회
    @GetMapping("/members/{memberId}/terms")
    public ApiResponse<PageResponse<TermResDTO.MemberTermItem>> getMemberTerms(
            @PathVariable Long memberId,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return ApiResponse.onSuccess(TermSuccessCode.TERM_LIST_FETCHED,
                termService.getMemberTerms(memberId, pageable));
    }
}
