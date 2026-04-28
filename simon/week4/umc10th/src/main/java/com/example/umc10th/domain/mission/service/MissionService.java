package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    public MissionResDTO.MissionList getMissions(String status, int page, int size) {
        MissionResDTO.MissionItem item = MissionResDTO.MissionItem.builder()
                .missionId(1L)
                .title("미션 제목")
                .reward("1000P")
                .createdAt(LocalDateTime.now().toString())
                .build();

        return MissionResDTO.MissionList.builder()
                .missions(List.of(item))
                .page(page)
                .size(size)
                .hasNext(false)
                .build();
    }

    public MissionResDTO.Challenge challengeMission(Long missionId, MissionReqDTO.Challenge dto) {
        return MissionResDTO.Challenge.builder()
                .missionId(missionId)
                .message("미션 도전이 시작되었습니다.")
                .build();
    }
}
