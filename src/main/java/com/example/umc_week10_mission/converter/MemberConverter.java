package com.example.umc_week10_mission.converter;

import com.example.umc_week10_mission.domain.Review;
import com.example.umc_week10_mission.domain.mapping.MemberMission;
import com.example.umc_week10_mission.dto.MemberResponseDTO;
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.stream.Collectors;

public class MemberConverter {

    public static MemberResponseDTO.ReviewDTO toReviewDTO(Review review) {
        return MemberResponseDTO.ReviewDTO.builder()
                .reviewId(review.getId())
                .storeName(review.getStore().getName())
                .body(review.getBody())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .build();
    }

    public static MemberResponseDTO.ReviewListDTO toReviewListDTO(Page<Review> reviewPage) {
        List<MemberResponseDTO.ReviewDTO> reviewDTOList = reviewPage.stream()
                .map(MemberConverter::toReviewDTO)
                .collect(Collectors.toList());

        return MemberResponseDTO.ReviewListDTO.builder()
                .reviewList(reviewDTOList)
                .listSize(reviewDTOList.size())
                .totalPage(reviewPage.getTotalPages())
                .totalElements(reviewPage.getTotalElements())
                .isFirst(reviewPage.isFirst())
                .isLast(reviewPage.isLast())
                .build();
    }

    public static MemberResponseDTO.MyMissionDTO toMyMissionDTO(MemberMission memberMission) {
        return MemberResponseDTO.MyMissionDTO.builder()
                .missionId(memberMission.getMission().getId())
                .storeName(memberMission.getMission().getStore().getName())
                .reward(memberMission.getMission().getReward())
                .status(memberMission.getStatus().toString())
                .build();
    }

    public static MemberResponseDTO.MyMissionListDTO toMyMissionListDTO(Page<MemberMission> myMissionPage) {
        List<MemberResponseDTO.MyMissionDTO> myMissionDTOList = myMissionPage.stream()
                .map(MemberConverter::toMyMissionDTO)
                .collect(Collectors.toList());

        return MemberResponseDTO.MyMissionListDTO.builder()
                .missionList(myMissionDTOList)
                .listSize(myMissionDTOList.size())
                .totalPage(myMissionPage.getTotalPages())
                .totalElements(myMissionPage.getTotalElements())
                .isFirst(myMissionPage.isFirst())
                .isLast(myMissionPage.isLast())
                .build();
    }
}