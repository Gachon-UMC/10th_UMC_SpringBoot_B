package org.example.umc10thyongjae.domain.mission.controller;

import lombok.RequiredArgsConstructor;
import org.example.umc10thyongjae.domain.mission.enums.MissionStatus;
import org.example.umc10thyongjae.domain.mission.dto.request.ReqMissionCompleteRequestDto;
import org.example.umc10thyongjae.domain.mission.dto.response.MissionResponseDto;
import org.example.umc10thyongjae.domain.mission.dto.response.ReqMissionCompleteResponseDto;
import org.example.umc10thyongjae.domain.mission.dto.response.UserMissionResponseDto;
import org.example.umc10thyongjae.domain.mission.service.MissionService;
import org.example.umc10thyongjae.global.apiPayload.ApiResponse;
import org.example.umc10thyongjae.global.apiPayload.code.GeneralSuccessCode;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mission")
public class MissionController {
    private final MissionService missionService;

    @GetMapping()
    public ApiResponse<List<MissionResponseDto>> getMission(
            @RequestAttribute long userId,
            @RequestParam int page,
            @RequestParam int size
    ) {
        //UserId로 location 설정
        String location = "mapo";

        List<MissionResponseDto> result = missionService.getMission(location, page, size);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    @GetMapping("/user-mission")
    public ApiResponse<List<UserMissionResponseDto>> getUserMission(
            @RequestAttribute long userId,
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String status
    ) {
        List<UserMissionResponseDto> result = missionService.getUserMission(userId, page, size, status);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }


    @PostMapping("/{missionKey}/request-complete")
    public ApiResponse<ReqMissionCompleteResponseDto> requestMissionComplete(
            @RequestAttribute long userId,
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
