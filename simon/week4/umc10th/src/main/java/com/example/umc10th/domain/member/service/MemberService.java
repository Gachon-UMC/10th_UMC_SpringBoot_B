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

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    // 회원가입
    public MemberResDTO.Register register() {
        return null;
    }

    // 로그인
    public MemberResDTO.Login login() {
        return null;
    }

    // 마이페이지
    public MemberResDTO.GetInfo getInfo(MemberReqDTO.GetInfo dto) {
        // DTO에서 유저 ID를 추출
        Long memberId = dto.id();

        // DB에서 해당 유저 ID로 데이터 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // 컨버터를 이용해서 응답 DTO 생성 & return
        return MemberConverter.toGetInfo(member);
    }

    // 회원정보 수정
    @Transactional
    public MemberResDTO.UpdateInfo updateInfo(MemberReqDTO.UpdateInfo dto) {
        // 1. 수정할 유저 찾기 (지금은 임시로 null 처리하거나 로직 구현)
        // 2. 유저 정보 변경
        // 3. 결과 반환
        return MemberResDTO.UpdateInfo.builder()
                .memberId(1L) // 임시 ID
                .updatedAt(LocalDateTime.now())
                .build();
    }

    // 회원 탈퇴
    @Transactional
    public void deleteMember() {
        // 탈퇴 로직 (soft delete 등) 구현
    }
}
