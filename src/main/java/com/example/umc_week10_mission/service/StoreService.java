package com.example.umc_week10_mission.service;

import com.example.umc_week10_mission.domain.Mission;
import com.example.umc_week10_mission.domain.Store;
import com.example.umc_week10_mission.repository.MissionRepository;
import com.example.umc_week10_mission.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService {

    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

    public Page<Mission> getMissions(Long storeId, Integer page) {
        // 1. 가게 존재 여부 확인
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 가게가 존재하지 않습니다."));

        // 2. 페이징 객체 생성 (한 페이지당 10개)
        PageRequest pageRequest = PageRequest.of(page, 10);

        // 3. 미션 목록 조회 및 반환
        return missionRepository.findAllByStore(store, pageRequest);
    }
}