package com.example.umc._th.domain.member.service;

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
    @Transactional()
    public MemberResDTO.GetInfo getInfo(Long memberId) {
            Member member = memberRepository.findById(memberId)
                    .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

            return MemberResDTO.GetInfo.builder()
                    .name(member.getName())
                    .email(member.getEmail())
                    .phoneNum(member.getPhoneNum())
                    .point(member.getPoint())
                    .birth(member.getBirth())
                    .address(member.getAddress())
                    .build();
    }

    @Transactional
    public MemberResDTO.Signup signup(MemberReqDTO.Signup request) {

        if(request.email() == null || request.email().isBlank()){
            throw new MemberException(MemberErrorCode.INVALID_MEMBER_REQUEST);
        }

        boolean isExists = memberRepository.existsByEmail(request.email());
        if(isExists){
            throw new MemberException(MemberErrorCode.MEMBER_ALREADY_EXISTS);
        }

        Member member = Member.builder()
                .name(request.name())
                .email(request.email())
                .phoneNum(request.phoneNum())
                .gender(request.gender())
                .birth(request.birth())
                .address(request.address())
                .socialType(request.socialType())
                .build();

        Member savedMember = memberRepository.save(member);

        return new MemberResDTO.Signup(savedMember.getId());
    }
}
