package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.api.MemberApiSpecification;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class MemberController implements MemberApiSpecification {

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<MemberResDTO.SignupResponse>> signup(
            @Valid @RequestBody MemberReqDTO.SignupRequest request
    ) {
        MemberResDTO.SignupResponse response = null;
        return ResponseEntity
                .status(MemberSuccessCode.MEMBER_SIGNUP_SUCCESS.getStatus())
                .body(ApiResponse.onSuccess(MemberSuccessCode.MEMBER_SIGNUP_SUCCESS, response));
    }
}
