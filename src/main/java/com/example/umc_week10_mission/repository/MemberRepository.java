package com.example.umc_week10_mission.repository;

import com.example.umc_week10_mission.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // 기본 findById 제공
}