package com.example.umc10th.domain.term.converter;

import com.example.umc10th.domain.term.dto.TermResDTO;
import com.example.umc10th.domain.term.entity.MemberTerm;
import com.example.umc10th.domain.term.entity.Term;
import com.example.umc10th.global.apiPayload.dto.PageResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public class TermConverter {
    // 전체 약관 목록을 조회하거나, 약관 상세 내용을 보여줄 때
    public static TermResDTO.TermItem toTermItem(Term t) {
        return TermResDTO.TermItem.builder()
                .id(t.getId())
                .termName(t.getTermName())
                .content(t.getContent())
                .optional(t.getOptional())
                .createdAt(t.getCreatedAt())
                .build();
    }

    public static PageResponse<TermResDTO.MemberTermItem> toMemberTermPage(Page<MemberTerm> memberTerms) {
        List<TermResDTO.MemberTermItem> data = memberTerms.getContent().stream()
                .map(TermConverter::toMemberTermItem)
                .toList();

        return PageResponse.of(memberTerms, data); // 전역 표준 활용
    }

    // MemberTerm에서 누가 어떤 약관에 언제 동의했는지 정보를 추출하여 DTO로 변환
    public static TermResDTO.MemberTermItem toMemberTermItem(MemberTerm mm) {
        return TermResDTO.MemberTermItem.builder()
                .memberTermId(mm.getId())
                .termId(mm.getTerm().getId())
                .termName(mm.getTerm().getTermName())
                .agreedAt(mm.getCreatedAt())
                .build();
    }
}
