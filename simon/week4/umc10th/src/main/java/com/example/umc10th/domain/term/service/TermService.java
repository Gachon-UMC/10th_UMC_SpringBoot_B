package com.example.umc10th.domain.term.service;

import com.example.umc10th.domain.term.converter.TermConverter;
import com.example.umc10th.domain.term.dto.TermResDTO;
import com.example.umc10th.domain.term.entity.MemberTerm;
import com.example.umc10th.domain.term.repository.MemberTermRepository;
import com.example.umc10th.global.apiPayload.dto.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TermService {
    private final MemberTermRepository memberTermRepository;

    /**
     * 사용자별 약관 동의 내역 페이징 조회
     */
    public PageResponse<TermResDTO.MemberTermItem> getMemberTerms(Long memberId, Pageable pageable) {
        // 1-based index 보정
        Pageable adjustedPageable = PageRequest.of(
                Math.max(0, pageable.getPageNumber() - 1),
                pageable.getPageSize(),
                pageable.getSort()
        );

        Page<MemberTerm> memberTerms = memberTermRepository.findAllByMemberId(memberId, adjustedPageable);

        // 공통 규격으로 변환하여 반환
        return TermConverter.toMemberTermPage(memberTerms);
    }
}
