package com.example.umc10th.domain.member_mission.dto;

import com.example.umc10th.domain.member_mission.enums.MissionStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

public class MemberMissionReqDTO {
    @Builder
    public record MyMissionRequest(
            @NotNull(message = "사용자 ID는 필수 입력 사항입니다.")
            Long memberId,

            @NotNull(message = "미션 상태(ONGOING/DONE)를 지정해주세요.")
            MissionStatus missionStatus,

            @Min(value = 1, message = "페이지 번호는 1 이상이어야 합니다.")
            Integer page,

            @Min(value = 1, message = "페이지 크기는 1 이상이어야 합니다.")
            Integer size
    ) {}
}
