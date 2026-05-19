package com.example.umc10th.global.security;

import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th.global.apiPayload.code.GeneralErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

// 권한이 부족한 사용자의 요청에 대한 응답을 통일합니다.
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    // 인가 실패 시 공통 에러 응답을 JSON으로 반환합니다.
    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException
    ) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        BaseErrorCode code = GeneralErrorCode.FORBIDDEN;

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(code.getStatus().value());
        objectMapper.writeValue(response.getOutputStream(), ApiResponse.onFailure(code, null));
    }
}
