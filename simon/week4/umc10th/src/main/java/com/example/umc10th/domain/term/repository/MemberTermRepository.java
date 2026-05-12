package com.example.umc10th.domain.term.repository;

import com.example.umc10th.domain.term.entity.MemberTerm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberTermRepository extends JpaRepository<MemberTerm, Long> {
    // 페이징 처리가 된 사용자별 약관 동의 내역 조회
    Page<MemberTerm> findAllByMemberId(Long memberId, Pageable pageable);

    // 사용자별 약관 동의 내역 전체 조회
    // 페이징 없이 특정 회원이 동의한 모든 내역을 List 형태로 한꺼번에 가져온다.
    List<MemberTerm> findAllByMemberId(Long memberId);

    // 페치 조인을 이용한 최적화 조회
    @Query("select mm from MemberTerm mm join fetch mm.term where mm.member.id = :memberId")
    List<MemberTerm> findAllByMemberIdWithTerm(Long memberId);
}
