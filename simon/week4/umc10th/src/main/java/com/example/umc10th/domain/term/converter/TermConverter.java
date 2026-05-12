package com.example.umc10th.domain.term.converter;

import com.example.umc10th.domain.term.dto.TermResDTO;
import com.example.umc10th.domain.term.entity.MemberTerm;
import com.example.umc10th.domain.term.entity.Term;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

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

    // 사용자별 동의 약관 페이지 변환
    // 사용자가 동의한 약관 리스트(Page<MemberTerm>)를 페이징 정보가 포함된 DTO로 변환
    public static TermResDTO.MemberTermList toMemberTermListDTO(Page<MemberTerm> memberTerms) {

        List<TermResDTO.MemberTermItem> termItems = memberTerms.stream()
                .map(TermConverter::toMemberTermItem)
                .collect(Collectors.toList());

        return TermResDTO.MemberTermList.builder()
                .items(termItems)
                .page(memberTerms.getNumber())
                .size(memberTerms.getSize())
                .isFirst(memberTerms.isFirst())
                .isLast(memberTerms.isLast())
                .totalPage(memberTerms.getTotalPages())
                .totalElements(memberTerms.getTotalElements())
                .build();
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
