package com.example.umc_week10_mission.repository;

import com.example.umc_week10_mission.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}