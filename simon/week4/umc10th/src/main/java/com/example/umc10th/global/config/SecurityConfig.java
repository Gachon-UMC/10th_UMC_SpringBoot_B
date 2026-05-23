package com.example.umc10th.global.config;

import com.example.umc10th.global.security.filter.JwtAuthFilter;
import com.example.umc10th.global.security.handler.CustomAccessDenied;
import com.example.umc10th.global.security.handler.CustomEntryPoint;
import com.example.umc10th.global.security.handler.OAuthSuccessHandler;
import com.example.umc10th.global.security.service.CustomOAuthService;
import com.example.umc10th.global.security.service.CustomUserDetailsService;
import com.example.umc10th.global.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;
    private final CustomOAuthService customOAuthService;

    private final String[] allowUris = {
            // Swagger 허용
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",

            // 로컬 회원가입 및 로그인 API 허용
            "/auth/**",
            "/api/v1/auth/**",

            // 커스텀 OAuth2 진입점 및 콜백 주소
            "/oauth/authorize/**",
            "/oauth/callback/*",

            // 런타임 에러 출력용 (401 덮어쓰기 방어)
            "/error"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)

                // URI 허용 여부
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(allowUris).permitAll()         // Public API: 인증 없이 전원 패스
                        .anyRequest().authenticated()                     // Private API: 그 외 모든 요청은 락(Lock)
                )

                // Pure REST API 최적화 (세션/폼로그인 전면 폐쇄)
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement(AbstractHttpConfigurer::disable)

                // JWT 커스텀 필터를 인증 필터 체인 맨 앞에 장착
                .addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class)

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )

                // OAuth
                .oauth2Login(oauth -> oauth
                        // 인증 엔트리 포인트
                        .authorizationEndpoint(auth -> auth
                                .baseUri("/oauth/authorize")
                        )

                        // 콜백 주소
                        .redirectionEndpoint(redirect -> redirect
                                .baseUri("/oauth/callback/**")
                        )

                        // 인증 완료 후 정보 활용
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuthService)
                        )

                        // 성공 시 JWT 토큰 발행할 핸들러
                        .successHandler(oAuthSuccessHandler())

                        // 실패 시 /login으로 도망가는 걸 막고, 진짜 에러 원인을 화면에 출력
                        .failureHandler((request, response, exception) -> {
                            response.setContentType("application/json;charset=UTF-8");
                            response.setStatus(jakarta.servlet.http.HttpServletResponse.SC_UNAUTHORIZED);
                            response.getWriter().write("{\"isSuccess\":false, \"message\":\"로그인 에러 원인: " + exception.getMessage() + "\"}");
                        })
                )

                // 예외 상황 핸들러
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(customAccessDenied())
                        .authenticationEntryPoint(customEntryPoint())
                )
        ;
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomAccessDenied customAccessDenied() {
        return new CustomAccessDenied();
    }

    @Bean
    public CustomEntryPoint customEntryPoint() {
        return new CustomEntryPoint();
    }

    @Bean
    public JwtAuthFilter jwtAuthFilter() {
        return new JwtAuthFilter(jwtUtil, customUserDetailsService);
    }

    @Bean
    public OAuthSuccessHandler oAuthSuccessHandler() {
        return new OAuthSuccessHandler(jwtUtil);
    }
}
