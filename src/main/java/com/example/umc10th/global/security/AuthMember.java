package com.example.umc10th.global.security;

import com.example.umc10th.domain.member.entity.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

// Spring Security에서 인증된 회원 정보를 표현합니다.
@Getter
@RequiredArgsConstructor
public class AuthMember implements UserDetails {

    private final Member member;

    // 회원의 권한 목록을 반환합니다.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    // 회원의 암호화된 비밀번호를 반환합니다.
    @Override
    public String getPassword() {
        return member.getPassword();
    }

    // 로그인 식별자로 사용할 이메일을 반환합니다.
    @Override
    public String getUsername() {
        return member.getEmail();
    }

    // 계정 만료 여부를 반환합니다.
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정 잠금 여부를 반환합니다.
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀번호 만료 여부를 반환합니다.
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 활성화 여부를 반환합니다.
    @Override
    public boolean isEnabled() {
        return true;
    }
}
