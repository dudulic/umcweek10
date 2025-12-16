package com.example.umc_week10_mission.repository;

import com.example.umc_week10_mission.domain.Mission;
import com.example.umc_week10_mission.domain.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    // 특정 가게의 미션 목록 조회 (페이징)
    Page<Mission> findAllByStore(Store store, Pageable pageable);
}