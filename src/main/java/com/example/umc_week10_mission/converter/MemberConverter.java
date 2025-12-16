package com.example.umc_week10_mission.converter;

import com.example.umc_week10_mission.domain.Member;
import com.example.umc_week10_mission.domain.enums.Role;
import com.example.umc_week10_mission.dto.MemberReqDTO;
import com.example.umc_week10_mission.dto.MemberResponseDTO;

public class MemberConverter {

    public static Member toMember(MemberReqDTO.JoinDTO dto, String encodedPassword, Role role) {
        return Member.builder()
                .name(dto.name())
                .email(dto.email())
                .password(encodedPassword)
                .role(role)
                .birth(dto.birth())
                .address(dto.address())
                .detailAddress(dto.specAddress())
                .build();
    }

    // 회원가입 응답 DTO 변환 (예시)
    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member) {
        return MemberResponseDTO.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(java.time.LocalDateTime.now()) // BaseEntity 사용 시 member.getCreatedAt()
                .build();
    }
}