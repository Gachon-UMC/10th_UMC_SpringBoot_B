package com.example.umc10th.domain.term.service;

import com.example.umc10th.domain.term.entity.MemberTerm;
import com.example.umc10th.domain.term.repository.MemberTermRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TermService {
    private final MemberTermRepository memberTermRepository;

    // 사용자별 약관 동의 내역 페이징 조회
    public Page<MemberTerm> getMemberTerms(Long memberId, int page, int size) {
        return memberTermRepository.findAllByMemberId(memberId, PageRequest.of(page, size));
    }
}
