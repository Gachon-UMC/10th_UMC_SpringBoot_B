package com.example.umc10th.domain.term.repository;

import com.example.umc10th.domain.term.entity.MemberTerm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberTermRepository extends JpaRepository<MemberTerm, Long> {
    // 특정 사용자의 약관 동의 내역을 페이징 조회 (Fetch Join 적용)
    @Query("""
        select mt from MemberTerm mt
        join fetch mt.term
        where mt.member.id = :memberId
    """)
    Page<MemberTerm> findAllByMemberId(@Param("memberId") Long memberId, Pageable pageable);

    // 사용자별 약관 동의 내역 전체 조회 (리스트 형태)
    @Query("select mt from MemberTerm mt join fetch mt.term where mt.member.id = :memberId")
    List<MemberTerm> findAllByMemberIdWithTerm(@Param("memberId") Long memberId);;
}
