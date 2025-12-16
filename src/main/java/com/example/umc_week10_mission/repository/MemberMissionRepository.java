package com.example.umc_week10_mission.repository;

import com.example.umc_week10_mission.domain.Member;
import com.example.umc_week10_mission.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    // 내가 작성한 리뷰 목록 조회 (페이징)
    Page<Review> findAllByMember(Member member, Pageable pageable);
}