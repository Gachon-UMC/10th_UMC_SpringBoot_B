package org.example.umc10thyongjae.global.apiPayload.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.umc10thyongjae.global.apiPayload.ApiResponse;
import org.example.umc10thyongjae.global.apiPayload.code.BaseErrorCode;
import org.example.umc10thyongjae.global.apiPayload.code.GeneralErrorCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import tools.jackson.databind.ObjectMapper;
import java.io.IOException;

public class CustomAccessDenied implements AccessDeniedHandler {
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

        ApiResponse<Void> errorResponse = ApiResponse.onFailure(code,null);

        objectMapper.writeValue(response.getOutputStream(), errorResponse);
    }
}
