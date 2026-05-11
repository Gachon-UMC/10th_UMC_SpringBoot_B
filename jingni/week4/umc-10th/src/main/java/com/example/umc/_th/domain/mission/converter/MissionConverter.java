package com.example.umc._th.domain.mission.converter;

import com.example.umc._th.domain.mission.dto.MissionDTO;

import com.example.umc._th.domain.mission.entity.Mission;

import com.example.umc._th.domain.mission.enums.Status;

import com.example.umc._th.domain.store.dto.StoreDTO;

public class MissionConverter {

    public static MissionDTO.Mission toMissionDTO(Mission mission, Status status) {

        return new MissionDTO.Mission(

                mission.getMissionId(),

                mission.getDeadline(),

                mission.getPoint(),

                status,

                new StoreDTO.StoreSummary(

                        mission.getStore().getName(),

                        mission.getStore().getFood().getType()

                )

        );

    }

}