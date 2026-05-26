package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.HomeResponseDTO;
import com.example.umc10th.domain.member.dto.UserJoinRequestDTO;
import com.example.umc10th.domain.member.dto.UserJoinResponseDTO;
import com.example.umc10th.domain.member.dto.UserLoginRequestDTO;
import com.example.umc10th.domain.member.dto.UserLoginResponseDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.global.security.AuthMember;
import com.example.umc10th.global.security.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// 회원 도메인의 비즈니스 로직을 처리합니다.
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // 회원가입 비즈니스 로직을 처리합니다.
    @Transactional
    public UserJoinResponseDTO join(UserJoinRequestDTO request) {
        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new MemberException(MemberErrorCode.MEMBER_EMAIL_ALREADY_EXISTS);
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        Member member = MemberConverter.toMember(request, encodedPassword);
        Member savedMember = memberRepository.save(member);

        return MemberConverter.toUserJoinResponseDTO(savedMember);
    }

    // 로그인 요청을 검증하고 JWT Access Token을 발급합니다.
    @Transactional
    public UserLoginResponseDTO login(UserLoginRequestDTO request) {
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.MEMBER_PASSWORD_NOT_MATCH);
        }

        String accessToken = jwtUtil.createAccessToken(new AuthMember(member));
        return MemberConverter.toUserLoginResponseDTO(accessToken);
    }

    // 회원 ID로 마이페이지 정보를 조회합니다.
    public HomeResponseDTO getMyPage(Long userId) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        return MemberConverter.toHomeResponseDTO(member);
    }

    // 인증 객체의 회원 정보로 마이페이지를 조회합니다.
    public HomeResponseDTO getMyPage(AuthMember authMember) {
        return MemberConverter.toHomeResponseDTO(authMember.getMember());
    }
}
