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
    public MemberResDTO.GetInfo getInfo(Member member) {
        // 필터가 이미 긁어온 객체의 탈퇴 여부(INACTIVE)를 안전하게 1차 검증
        if (member == null || member.getMemberStatus() == com.example.umc10th.domain.member.enums.MemberStatus.INACTIVE) {
            throw new MemberException(MemberErrorCode.MEMBER_NOT_FOUND);
        }

        // 컨버터를 이용해서 DTO로 변환 후 리턴
        return MemberConverter.toGetInfo(member);
    }

    /**
     * 회원정보 수정
     */
    @Transactional
    public MemberResDTO.UpdateInfo updateInfo(Member member, MemberReqDTO.UpdateInfo dto) {
        Member activeMember = memberRepository.findById(member.getId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // 더티 체킹을 통한 수정
        activeMember.updateProfile(dto.phoneNumber(), dto.profileUrl());

        return MemberConverter.toUpdateInfoResDTO(activeMember);
    }

    /**
     * 회원 탈퇴
     */
    @Transactional
    public void deleteMember(Member member) {
        Member activeMember = memberRepository.findById(member.getId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        activeMember.withdraw();
    }
}