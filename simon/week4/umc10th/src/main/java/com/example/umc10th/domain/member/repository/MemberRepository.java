package com.example.umc10th.domain.member.repository;

import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("""
            select new com.example.umc10th.domain.member.dto.MemberResDTO$GetInfo(
                m.name,
                m.profileUrl,
                m.email,
                m.phoneNumber,
                m.point
            )
            from Member m
            where m.id = :memberId
            """)
    Optional<MemberResDTO.GetInfo> findGetInfoById(Long memberId);
}
