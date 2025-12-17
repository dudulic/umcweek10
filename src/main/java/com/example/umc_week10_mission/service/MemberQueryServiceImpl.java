package com.example.umc_week10_mission.service;

import com.example.umc_week10_mission.domain.Member;
import com.example.umc_week10_mission.dto.MemberReqDTO;
import com.example.umc_week10_mission.dto.MemberResponseDTO;
import com.example.umc_week10_mission.repository.MemberRepository;
import com.example.umc_week10_mission.service.auth.CustomUserDetails;
import com.example.umc_week10_mission.service.auth.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    public MemberResponseDTO.LoginDTO login(MemberReqDTO.LoginDTO request) {
        // 1. 이메일로 회원 조회
        Member member = memberRepository.findByEmail(request.email())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다.")); // Custom Exception 사용 권장

        // 2. 비밀번호 확인
        if (!passwordEncoder.matches(request.password(), member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다."); // Custom Exception 사용 권장
        }

        // 3. 토큰 생성
        CustomUserDetails userDetails = new CustomUserDetails(member);
        String accessToken = jwtUtil.createAccessToken(userDetails);

        // 4. 응답 반환
        return MemberResponseDTO.LoginDTO.builder()
                .memberId(member.getId())
                .accessToken(accessToken)
                .build();
    }
}