package com.example.umc10th.global.security.util;

import com.example.umc10th.domain.member.enums.SocialType;
import com.example.umc10th.global.security.entity.AuthMember;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtUtil {
    private final SecretKey secretKey;
    private final Duration accessExpiration;

    public JwtUtil(
            @Value("${jwt.token.secretKey}") String secret,
            @Value("${jwt.token.expiration.access}") Long accessExpiration
    ) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.accessExpiration = Duration.ofMillis(accessExpiration);
    }

    // 유저 정보(AuthMember)를 기반으로 AccessToken 생성
    public String createAccessToken(AuthMember member) {
        return createToken(member, accessExpiration);
    }

    /**
     * 토큰에서 유저 고유 식별자(이메일 또는 소셜 UID) 추출
     * * @param token JWT 토큰 문자열
     *
     * @return Subject에 바인딩된 유저 고유 식별자 uid
     */
    public String getUid(String token) {
        try {
            return getClaims(token).getPayload().getSubject();
        } catch (JwtException e) {
            return null;
        }
    }

    /**
     * 전달받은 토큰의 위변조 여부 및 만료 시간 검증
     *
     * @param token 검증할 JWT 토큰 문자열
     * @return 유효성 여부 (True / False)
     */
    public boolean isValid(String token) {
        try {
            getClaims(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    /**
     * JWT AccessToken 기계 조립 및 암호화 서명 발행
     */
    private String createToken(AuthMember authMember, Duration expiration) {
        Instant now = Instant.now();

        // 인가 정보
        String authorities = authMember.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        // 개선된 토큰 생성 로직 (createToken 내부)
        String socialTypeStr = (authMember.getMember().getSocialType() != null)
                ? authMember.getMember().getSocialType().name()
                : "COMMON"; // null 방어

        return Jwts.builder()
                .subject(authMember.getUsername())
                .claim("role", authorities)
                .claim("social_type", socialTypeStr)
                .issuedAt(Date.from(now))                       // 언제 발급한지
                .expiration(Date.from(now.plus(expiration)))    // 언제까지 유효한지
                .signWith(secretKey)                            // sign할 Key
                .compact();
    }

    /**
     * 시크릿 키를 기반으로 토큰 평문 복호화 및 클레임 데이터 분리 파싱
     */
    private Jws<Claims> getClaims(String token) throws JwtException {
        return Jwts.parser()
                .verifyWith(secretKey)
                .clockSkewSeconds(60)
                .build()
                .parseSignedClaims(token);
    }

    /**
     * 토큰 내부에 커스텀 클레임으로 심어둔 소셜 공급자 타입(SocialType) 추출
     *
     * @param token 파싱할 JWT 토큰 문자열
     * @return SocialType 에넘 값 (일반 유저일 경우 COMMON 반환)
     */
    public SocialType getSocialType(String token) {
        try {
            Object typeObj = getClaims(token).getPayload().get("social_type");

            if (typeObj == null || "COMMON".equals(typeObj.toString())) {
                return SocialType.COMMON;
            }
            return SocialType.valueOf(typeObj.toString().toUpperCase());
        } catch (JwtException e) {
            return SocialType.COMMON;
        }
    }
}