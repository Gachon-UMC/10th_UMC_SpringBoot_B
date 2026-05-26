package org.example.umc10thyongjae.global.apiPayload.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

//TODO- @AuthenticationPrincipal AuthUser authUser로 전체 대체 후 제거
@Deprecated
public class HeaderAuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("Authorization");

        long userId = 1;

        request.setAttribute("userId", userId);

        return true;
    }
}