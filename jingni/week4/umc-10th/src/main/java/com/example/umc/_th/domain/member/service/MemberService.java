package com.example.umc._th.domain.member.service;

import com.example.umc._th.domain.member.converter.MemberConverter;
import com.example.umc._th.domain.member.dto.MemberReqDTO;
import com.example.umc._th.domain.member.dto.MemberResDTO;
import com.example.umc._th.domain.member.entity.Member;
import com.example.umc._th.domain.member.exception.MemberException;
import com.example.umc._th.domain.member.exception.code.MemberErrorCode;
import com.example.umc._th.domain.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    @Transactional
    public MemberResDTO.GetInfo getInfo(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        return MemberConverter.toGetInfo(member);
    }

    @Transactional
    public MemberResDTO.Signup signup(MemberReqDTO.Signup request) {

        if(request.email() == null || request.email().isBlank()){
            throw new MemberException(MemberErrorCode.INVALID_MEMBER_REQUEST);
        }

        if(memberRepository.existsByEmail(request.email())){
            throw new MemberException(MemberErrorCode.MEMBER_ALREADY_EXISTS);
        }

        Member member = MemberConverter.toEntity(request);

        Member savedMember = memberRepository.save(member);

        return MemberConverter.toSignup(savedMember);
    }
}
