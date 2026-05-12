package com.example.umc10th.domain.mission.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

// 미션 도메인 요청 DTO를 관리합니다.
public class MissionReqDTO {

    // 진행중인 미션 목록 조회 요청 DTO입니다.
    @Getter
    public static class InProgressMissionListDTO {

        // 조회할 사용자 ID입니다.
        @NotNull(message = "사용자 ID는 필수입니다.")
        private Long userId;

        // 한 페이지에 조회할 데이터 개수입니다.
        @NotNull(message = "페이지 크기는 필수입니다.")
        @Min(value = 1, message = "페이지 크기는 1 이상이어야 합니다.")
        private Integer pageSize;

        // 조회할 페이지 번호입니다.
        @NotNull(message = "페이지 번호는 필수입니다.")
        @Min(value = 0, message = "페이지 번호는 0 이상이어야 합니다.")
        private Integer pageNumber;

        // 정렬 기준 필드입니다.
        @Pattern(regexp = "id", message = "정렬 기준은 id만 가능합니다.")
        private String sort;
    }
}
