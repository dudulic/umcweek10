package com.example.umc_week10_mission.service;

import com.example.umc_week10_mission.converter.MemberConverter;
import com.example.umc_week10_mission.domain.Member;
import com.example.umc_week10_mission.domain.enums.Role;
import com.example.umc_week10_mission.dto.MemberReqDTO;
import com.example.umc_week10_mission.dto.MemberResponseDTO;
import com.example.umc_week10_mission.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public MemberResponseDTO.JoinResultDTO signup(MemberReqDTO.JoinDTO request) {
        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(request.password());

        // Member 엔티티 생성 및 저장
        Member member = MemberConverter.toMember(request, encodedPassword, Role.ROLE_USER);
        Member savedMember = memberRepository.save(member);

        return MemberConverter.toJoinResultDTO(savedMember);
    }
}