package com.example.umc10th.global.apiPayload;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

// API 공통 응답 필드 조회를 위한 Getter를 생성합니다.
@Getter
// 모든 필드를 받는 생성자를 생성합니다.
@AllArgsConstructor
public class ApiResponse<T> {

    // 요청 성공 여부입니다.
    private final boolean isSuccess;

    // 응답 코드입니다.
    private final String code;

    // 응답 메시지입니다.
    private final String message;

    // 실제 응답 데이터입니다.
    private final T result;

    // 성공 응답을 공통 형식으로 생성합니다.
    public static <T> ApiResponse<T> onSuccess(BaseSuccessCode code, T result) {
        return new ApiResponse<>(
                true,
                code.getCode(),
                code.getMessage(),
                result
        );
    }

    // 실패 응답을 공통 형식으로 생성합니다.
    public static <T> ApiResponse<T> onFailure(BaseErrorCode code, T result) {
        return new ApiResponse<>(
                false,
                code.getCode(),
                code.getMessage(),
                result
        );
    }
}
