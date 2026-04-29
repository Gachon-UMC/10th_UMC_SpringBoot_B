package org.example.umc10thyongjae.global.apiPayload;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.umc10thyongjae.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class ApiResponse<T> {

    @JsonPropertyOrder("isSuccess")
    private final Boolean isSuccess;

    @JsonPropertyOrder("code")
    private final String code;

    @JsonPropertyOrder("message")
    private final String message;

    @JsonPropertyOrder("result")
    private T result;

    public static <T> ApiResponse<T> onSuccess(BaseErrorCode code, T result) {
        return new ApiResponse<T>(true, code.getCode(), code.getMessage(), result);
    }

    public static <T> ApiResponse<T> onFailure(BaseErrorCode code, T result) {
        return new ApiResponse<T>(false, code.getCode(), code.getMessage(), result);
    }
}