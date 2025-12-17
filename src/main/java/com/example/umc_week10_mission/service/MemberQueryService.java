package com.example.umc_week10_mission.service;

import com.example.umc_week10_mission.dto.MemberReqDTO;
import com.example.umc_week10_mission.dto.MemberResponseDTO;

public interface MemberQueryService {
    MemberResponseDTO.LoginDTO login(MemberReqDTO.LoginDTO request);
}