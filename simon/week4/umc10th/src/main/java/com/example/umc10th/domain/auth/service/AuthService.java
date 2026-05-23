package com.example.umc10th.domain.auth.service;

import com.example.umc10th.domain.auth.converter.AuthConverter;
import com.example.umc10th.domain.auth.dto.AuthReqDTO;
import com.example.umc10th.domain.auth.dto.AuthResDTO;
import com.example.umc10th.domain.auth.exception.AuthException;
import com.example.umc10th.domain.auth.exception.code.AuthErrorCode;
import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.enums.MemberStatus;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.global.security.entity.AuthMember;
import com.example.umc10th.global.security.util.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

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

        // 저장하기 전에 비밀번호를 BCrypt로 암호화
        String encodedPassword = passwordEncoder.encode(dto.password());
        newMember.encodePassword(encodedPassword);

        // 생성된 엔티티를 DB에 영구 저장
        Member savedMember = memberRepository.save(newMember);

        // 저장된 정보를 응답 규격에 맞춰 DTO로 변환하여 반환
        return AuthConverter.toRegisterResDTO(savedMember);
    }

    /**
     * 사용자 인증 및 로그인
     */
    public AuthResDTO.Login login(AuthReqDTO.Login dto) {
        // 이메일로 사용자 조회
        Member member = memberRepository.findByEmail(dto.email())
                .orElseThrow(() -> new AuthException(AuthErrorCode.LOGIN_FAILED));

        // BCrypt 인코더의 matches 메서드로 암호화된 비밀번호 검증
        if (!passwordEncoder.matches(dto.password(), member.getPassword())) {
            throw new AuthException(AuthErrorCode.LOGIN_FAILED);
        }

        // 계정 유효성 체크 (활성화 상태 확인)
        if (member.getMemberStatus() == MemberStatus.INACTIVE) {
            throw new MemberException(MemberErrorCode.MEMBER_STATUS_INACTIVE);
        }

        // 인증에 성공한 유저 정보를 기반으로 시큐리티 전용 어댑터(통행증)인 AuthMember 객체 생성
        AuthMember authMember = new AuthMember(member);

        // 시큐리티 컨텍스트 등록용 Authentication 인증 토큰 발행
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                authMember, null, authMember.getAuthorities()
        );

        // 암호화 알고리즘을 거쳐 클라이언트에게 발급할 리얼 국경 통과용 AccessToken 생성
        String realAccessToken = jwtUtil.createAccessToken(authMember);

        // 현재 스레드의 SecurityContextHolder에 인증 객체를 영구 바인딩 (이후 수행되는 Private API 권한 패스용)
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 최종 성공 응답 규격 매핑: 조회된 유저 데이터와 리얼 JWT 토큰을 실어서 리턴
        return AuthConverter.toLoginResDTO(member, realAccessToken, "temp-refresh");
    }

    /**
     * 로그아웃 처리 수행, RefreshToken을 무효화
     */
    public AuthResDTO.LogoutResult logout(String authHeader) {
        String token = authHeader;

        // 헤더에 Bearer 접두사가 붙어있을 경우 순수 토큰 문자열만 싹 발라내는 방어 코드
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        }

        // jwtUtil을 활용하여 토큰 내부의 Subject 추출
        String email = jwtUtil.getUid(token);

        if (email == null || !jwtUtil.isValid(token)) {
            throw new AuthException(AuthErrorCode.TOKEN_INVALID);
        }

        // 소셜/일반 구분 없이 바로 이메일로 찌르기
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Long realMemberId = member.getId();
        SecurityContextHolder.clearContext();

        // 만약 DB에 RefreshToken을 저장 중이라면 여기서 삭제 처리
        // refreshTokenRepository.deleteByMemberId(realMemberId);

        // 유저의 ID를 컨버터에 태워서 리턴
        return MemberConverter.toLogoutResultDTO(realMemberId);
    }
}
