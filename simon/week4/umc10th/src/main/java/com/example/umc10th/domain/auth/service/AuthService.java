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

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private final MemberRepository memberRepository;

    /**
     * 신규 회원 가입
     */
    public AuthResDTO.Register register(AuthReqDTO.Register dto) {
        // 가입 여부 확인 (이메일 중복 체크)
        if (memberRepository.existsByEmail(dto.email())) {
            throw new MemberException(MemberErrorCode.EMAIL_ALREADY_EXIST);
        }

        // DTO 데이터를 기반으로 신규 회원 엔티티 생성 (Converter 활용)
        Member newMember = MemberConverter.toMember(dto);

        // 생성된 엔티티를 DB에 영구 저장
        Member savedMember = memberRepository.save(newMember);

        // 저장된 정보를 응답 규격에 맞춰 DTO로 변환하여 반환
        return MemberConverter.toRegisterResDTO(savedMember);
    }

    /**
     * 사용자 인증 및 로그인
     */
    public AuthResDTO.Login login(AuthReqDTO.Login dto) {
        // 이메일로 사용자 조회
        Member member = memberRepository.findByEmail(dto.email())
                .orElseThrow(() -> new MemberException(MemberErrorCode.LOGIN_FAILED));

        // 비밀번호 검증
        if (!member.getPassword().equals(dto.password())) {
            throw new MemberException(MemberErrorCode.LOGIN_FAILED);
        }

        // 계정 유효성 체크 (활성화 상태 확인)
        if (member.getMemberStatus() == MemberStatus.INACTIVE) {
            throw new MemberException(MemberErrorCode.MEMBER_STATUS_INACTIVE);
        }

        // 성공 시 DTO 반환
        return MemberConverter.toLoginResDTO(member);
    }

    /**
     * 로그아웃 처리 수행, RefreshToken을 무효화
     */
    public AuthResDTO.LogoutResult logout(String accessToken) {
        // TODO: 추후 JWT 라이브러리 연동 시 실제 memberId 추출 로직 구현 필요
        // Long memberId = jwtTokenProvider.getMemberId(accessToken);
        Long memberId = 1L; // 테스트용 임시 ID

        // DB에 저장된 해당 사용자의 RefreshToken 삭제
        // refreshTokenRepository.deleteByMemberId(memberId);

        // 결과 반환
        return MemberConverter.toLogoutResultDTO(memberId);
    }
}
