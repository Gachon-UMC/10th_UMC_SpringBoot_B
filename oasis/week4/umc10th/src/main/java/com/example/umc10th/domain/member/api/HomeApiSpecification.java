package com.example.umc10th.domain.member.api;

import com.example.umc10th.domain.member.dto.HomeResDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Home", description = "홈/마이페이지 관련 API")
public interface HomeApiSpecification {

    @Operation(summary = "홈 조회", description = "현재 선택된 지역에서 도전 가능한 미션 목록을 페이징하여 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "홈 조회 성공"),
            @ApiResponse(responseCode = "404", description = "회원을 찾을 수 없음")
    })
    ResponseEntity<com.example.umc10th.global.apiPayload.ApiResponse<HomeResDTO.HomeResponse>> home(
            @Parameter(description = "사용자 ID", example = "1") @PathVariable Long userId,
            @Parameter(description = "지역 ID", example = "1") @RequestParam Long regionId,
            @Parameter(description = "페이지 번호 (0부터 시작)", example = "0") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "페이지 크기", example = "10") @RequestParam(defaultValue = "10") int size
    );

    @Operation(summary = "마이페이지 조회", description = "사용자의 마이페이지 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "마이페이지 조회 성공"),
            @ApiResponse(responseCode = "404", description = "회원을 찾을 수 없음")
    })
    ResponseEntity<com.example.umc10th.global.apiPayload.ApiResponse<MemberResDTO.MypageResponse>> mypage(
            @Parameter(description = "사용자 ID", example = "1") @PathVariable Long userId
    );
}
