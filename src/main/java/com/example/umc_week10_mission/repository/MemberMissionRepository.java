package com.example.umc_week10_mission.repository;

import com.example.umc_week10_mission.domain.Member;
import com.example.umc_week10_mission.domain.enums.MissionStatus;
import com.example.umc_week10_mission.domain.mapping.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    // 내가 진행 중인 미션 목록 조회 (페이징)
    Page<MemberMission> findAllByMemberAndStatus(Member member, MissionStatus status, Pageable pageable);
}