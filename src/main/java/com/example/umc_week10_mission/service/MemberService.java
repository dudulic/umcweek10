package com.example.umc_week10_mission.service;

import com.example.umc_week10_mission.domain.Member;
import com.example.umc_week10_mission.domain.Review;
import com.example.umc_week10_mission.domain.enums.MissionStatus;
import com.example.umc_week10_mission.domain.mapping.MemberMission;
import com.example.umc_week10_mission.repository.MemberMissionRepository;
import com.example.umc_week10_mission.repository.MemberRepository;
import com.example.umc_week10_mission.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final MemberMissionRepository memberMissionRepository;

    // 내가 작성한 리뷰 목록 조회
    public Page<Review> getMyReviews(Long memberId, Integer page) {
        // 1. 회원 존재 여부 확인
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 회원이 존재하지 않습니다."));

        // 2. 페이징 객체 생성 (한 페이지당 10개)
        PageRequest pageRequest = PageRequest.of(page, 10);

        // 3. 리뷰 목록 조회 및 반환
        return reviewRepository.findAllByMember(member, pageRequest);
    }

    // 내가 진행중인 미션 목록 조회
    public Page<MemberMission> getMyChallengingMissions(Long memberId, Integer page) {
        // 1. 회원 존재 여부 확인
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 회원이 존재하지 않습니다."));

        // 2. 페이징 객체 생성 (한 페이지당 10개)
        PageRequest pageRequest = PageRequest.of(page, 10);

        // 3. 진행 중(CHALLENGING)인 미션 조회 및 반환
        return memberMissionRepository.findAllByMemberAndStatus(member, MissionStatus.CHALLENGING, pageRequest);
    }
}