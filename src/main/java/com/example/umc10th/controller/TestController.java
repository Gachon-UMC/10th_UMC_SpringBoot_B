package com.example.umc10th.controller;

import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Swagger와 서버 연결 상태를 확인하는 테스트 컨트롤러입니다.
@RestController
public class TestController {

    // 테스트 응답을 반환합니다.
    @GetMapping("/test")
    public ResponseEntity<ApiResponse<String>> test() {
        return ResponseEntity.ok(ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                "성공"
        ));
    }
}
