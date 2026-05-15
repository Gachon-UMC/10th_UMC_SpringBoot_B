package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    /**
     * 마이페이지
     */
    public MemberResDTO.GetInfo getInfo(Long memberId) {
        // 엔티티 조회
        Member member = memberRepository.findActiveMemberById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // Converter를 통해 DTO로 변환
        return MemberConverter.toGetInfo(member);
    }

    /**
     * 회원정보 수정
     */
    @Transactional
    public MemberResDTO.UpdateInfo updateInfo(Long memberId, MemberReqDTO.UpdateInfo dto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // 더티 체킹을 통한 수정
        member.updateProfile(dto.phoneNumber(), dto.profileUrl());

        return MemberConverter.toUpdateInfoResDTO(member);
    }

    /**
     * 회원 탈퇴
     */
    @Transactional
    public void deleteMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        member.withdraw();
    }
}