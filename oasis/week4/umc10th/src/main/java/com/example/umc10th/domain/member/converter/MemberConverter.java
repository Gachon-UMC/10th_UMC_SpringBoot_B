package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.HomeResDTO;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Gender;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.entity.Platform;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.MissionStatus;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class MemberConverter {

    public static Member toMember(MemberReqDTO.SignupRequest request, String encodedPassword, Gender gender) {
        return Member.builder()
                .email(request.email())
                .password(encodedPassword)
                .name(request.nickname())
                .gender(gender)
                .birthDate(request.birth())
                .platform(Platform.LOCAL)
                .isEnabled(true)
                .build();
    }

    public static MemberResDTO.SignupResponse toSignupResponse(Member member) {
        return MemberResDTO.SignupResponse.builder()
                .userId(member.getId())
                .email(member.getEmail())
                .nickname(member.getName())
                .build();
    }

    public static MemberResDTO.MypageResponse toMypageResponse(Member member) {
        int balance = member.getPoint() != null ? member.getPoint().getBalance().intValue() : 0;

        return MemberResDTO.MypageResponse.builder()
                .nickname(member.getName())
                .email(member.getEmail())
                .phone(member.getPhone())
                .phoneVerified(member.getPhone() != null)
                .point(balance)
                .build();
    }

    public static HomeResDTO.HomeResponse toHomeResponse(
            Member member,
            String regionName,
            Page<Mission> missionPage
    ) {
        int balance = member.getPoint() != null ? member.getPoint().getBalance().intValue() : 0;

        long completedCount = member.getMemberMissionList().stream()
                .filter(mm -> mm.getStatus() == MissionStatus.SUCCESS)
                .count();

        HomeResDTO.MissionProgressDTO missionProgress = HomeResDTO.MissionProgressDTO.builder()
                .current((int) completedCount)
                .total(10)
                .rewardPoint(1000)
                .build();

        List<HomeResDTO.MissionDTO> missions = missionPage.getContent().stream()
                .map(MemberConverter::toHomeMissionDTO)
                .toList();

        return HomeResDTO.HomeResponse.builder()
                .regionName(regionName)
                .point(balance)
                .missionProgress(missionProgress)
                .missions(missions)
                .currentPage(missionPage.getNumber())
                .totalPages(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .build();
    }

    private static HomeResDTO.MissionDTO toHomeMissionDTO(Mission mission) {
        int dDay = mission.getEndAt() != null
                ? (int) ChronoUnit.DAYS.between(LocalDate.now(), mission.getEndAt())
                : 0;

        return HomeResDTO.MissionDTO.builder()
                .missionId(mission.getId())
                .storeName(mission.getStore().getStoreName())
                .category(mission.getStore().getFoodCategory().getCategoryName())
                .condition(mission.getContent())
                .rewardPoint(mission.getReward().intValue())
                .dDay(dDay)
                .build();
    }
}
