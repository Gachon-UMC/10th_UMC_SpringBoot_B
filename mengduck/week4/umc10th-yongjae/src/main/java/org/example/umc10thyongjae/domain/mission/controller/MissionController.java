package org.example.umc10thyongjae.domain.mission.controller;

import org.example.umc10thyongjae.domain.mission.enums.MissionStatus;
import org.example.umc10thyongjae.domain.mission.dto.request.ReqMissionCompleteRequestDto;
import org.example.umc10thyongjae.domain.mission.dto.response.MissionResponseDto;
import org.example.umc10thyongjae.domain.mission.dto.response.ReqMissionCompleteResponseDto;
import org.example.umc10thyongjae.domain.mission.dto.response.UserMissionResponseDto;
import org.example.umc10thyongjae.global.apiPayload.ApiResponse;
import org.example.umc10thyongjae.global.apiPayload.code.GeneralSuccessCode;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mission")
public class MissionController {

    @GetMapping()
    public ApiResponse<List<MissionResponseDto>> getMission(
            @RequestAttribute int userKey,
            @RequestParam int page,
            @RequestParam int size
    ) {
        MissionResponseDto resultDto = MissionResponseDto.builder()
                .missionKey(123)
                .storeKey(7123)
                .storeName("마라탕")
                .storeCategory("중식")
                .reward(777)
                .rewardCondition(10000)
                .expireDate("2025-05-05").build();

        List<MissionResponseDto> result = new ArrayList<>();
        result.add(resultDto);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    @GetMapping("/user-mission")
    public ApiResponse<List<UserMissionResponseDto>> getUserMission(
            @RequestAttribute int userKey,
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String status
    ) {
        UserMissionResponseDto resultDto = UserMissionResponseDto.builder()
                .missionKey(123)
                .storeKey(7123)
                .storeName("마라탕")
                .storeCategory("중식")
                .reward(777)
                .rewardCondition(10000)
                .expireDate("2025-05-05")
                .status(MissionStatus.ONGOING).build();

        List<UserMissionResponseDto> result = new ArrayList<>();
        result.add(resultDto);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }


    @PostMapping("/{missionKey}/request-complete")
    public ApiResponse<ReqMissionCompleteResponseDto> requestMissionComplete(
            @RequestAttribute int userKey,
            @PathVariable long missionKey
    ) {
        ReqMissionCompleteResponseDto result = ReqMissionCompleteResponseDto
                .builder()
                .pinCode("1234567")
                .build();
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    @PostMapping("/{missionKey}/complete")
    public ApiResponse<Void> missionComplete(
            @RequestAttribute int userKey,
            @PathVariable long missionKey,
            @RequestBody ReqMissionCompleteRequestDto dto
            ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);
    }
}
