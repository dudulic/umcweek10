package com.example.umc_week10_mission.converter;

import com.example.umc_week10_mission.domain.Mission;
import com.example.umc_week10_mission.dto.StoreResponseDTO;
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.stream.Collectors;

public class StoreConverter {

    public static StoreResponseDTO.MissionDTO toMissionDTO(Mission mission) {
        return StoreResponseDTO.MissionDTO.builder()
                .id(mission.getId())
                .reward(mission.getReward())
                .missionSpec(mission.getMissionSpec())
                .deadline(mission.getDeadline().toString())
                .build();
    }

    public static StoreResponseDTO.MissionListDTO toMissionListDTO(Page<Mission> missionPage) {
        List<StoreResponseDTO.MissionDTO> missionDTOList = missionPage.stream()
                .map(StoreConverter::toMissionDTO)
                .collect(Collectors.toList());

        return StoreResponseDTO.MissionListDTO.builder()
                .missionList(missionDTOList)
                .isLast(missionPage.isLast())
                .isFirst(missionPage.isFirst())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .listSize(missionDTOList.size())
                .build();
    }
}