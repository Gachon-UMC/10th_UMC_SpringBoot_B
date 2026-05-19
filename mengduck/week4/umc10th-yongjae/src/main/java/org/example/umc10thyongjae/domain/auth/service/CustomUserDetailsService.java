package org.example.umc10thyongjae.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.example.umc10thyongjae.domain.auth.entity.User;
import org.example.umc10thyongjae.domain.auth.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + loginId));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getLoginId())
                .password(user.getPwd())
                .roles("USER")
                .disabled(!"N".equals(user.getDeleted()))
                .build();
    }
}
