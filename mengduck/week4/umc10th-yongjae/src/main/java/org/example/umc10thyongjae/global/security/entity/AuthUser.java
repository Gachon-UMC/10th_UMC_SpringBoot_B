package org.example.umc10thyongjae.global.security.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.umc10thyongjae.domain.auth.entity.User;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class AuthUser implements UserDetails {
    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public @Nullable String getPassword() {
        return user.getPwd();
    }

    @Override
    public String getUsername() {
        return user.getLoginId();
    }
}
