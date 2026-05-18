package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.HomeResponseDTO;
import com.example.umc10th.domain.member.dto.UserJoinRequestDTO;
import com.example.umc10th.domain.member.dto.UserJoinResponseDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// 해당 클래스를 Service 계층 Bean으로 등록합니다.
@Service
// final 필드를 생성자 주입 방식으로 주입합니다.
@RequiredArgsConstructor
public class MemberService {

    // 회원 Entity의 DB 접근을 담당하는 Repository입니다.
    private final MemberRepository memberRepository;

    // 회원가입 비즈니스 로직을 처리합니다.
    @Transactional
    public UserJoinResponseDTO join(UserJoinRequestDTO request) {
        Member member = MemberConverter.toMember(request);
        Member savedMember = memberRepository.save(member);

        return MemberConverter.toUserJoinResponseDTO(savedMember);
    }

    // 회원 홈/마이페이지 정보를 조회합니다.
    public HomeResponseDTO getMyPage(Long userId) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        return MemberConverter.toHomeResponseDTO(member);
    }
}
