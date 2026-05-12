package com.example.umc10th.domain.auth.service;

import com.example.umc10th.domain.auth.dto.AuthReqDTO;
import com.example.umc10th.domain.auth.dto.AuthResDTO;
import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.enums.MemberStatus;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private final MemberRepository memberRepository;

    // 회원가입
    public AuthResDTO.Register register(AuthReqDTO.Register dto) {
        // 1. 중복 검사
        if (memberRepository.existsByEmail(dto.email())) {
            throw new MemberException(MemberErrorCode.EMAIL_ALREADY_EXIST);
        }

        // 2. DTO -> Entity (Converter 활용)
        Member newMember = MemberConverter.toMember(dto);

        // 3. DB 저장
        Member savedMember = memberRepository.save(newMember);

        // 4. Entity -> Response DTO (Converter 활용)
        return MemberConverter.toRegisterResDTO(savedMember);
    }

    // 로그인
    public AuthResDTO.Login login(AuthReqDTO.Login dto) {
        // 1. 이메일로 사용자 조회
        Member member = memberRepository.findByEmail(dto.email())
                .orElseThrow(() -> new MemberException(MemberErrorCode.LOGIN_FAILED));

        // 2. 비밀번호 검증
        if (!member.getPassword().equals(dto.password())) {
            throw new MemberException(MemberErrorCode.LOGIN_FAILED);
        }

        // 3. 계정 상태 체크 (탈퇴 여부 등)
        if (member.getMemberStatus() == MemberStatus.INACTIVE) {
            throw new MemberException(MemberErrorCode.MEMBER_STATUS_INACTIVE);
        }

        // 3. 성공 시 DTO 반환
        return MemberConverter.toLoginResDTO(member);
    }

    // 로그아웃
    public AuthResDTO.LogoutResult logout(String accessToken) {
        // 1. 토큰에서 사용자 정보 추출 (추후 JWT 라이브러리 연동)
        // Long memberId = jwtTokenProvider.getMemberId(accessToken);
        Long memberId = 1L; // 테스트용 임시 ID

        // 2. DB에 저장된 해당 사용자의 RefreshToken 삭제
        // refreshTokenRepository.deleteByMemberId(memberId);

        // 3. 결과 반환
        return MemberConverter.toLogoutResultDTO(memberId);
    }
}
