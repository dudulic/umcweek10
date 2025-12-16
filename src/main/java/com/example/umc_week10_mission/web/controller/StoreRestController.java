package com.example.umc_week10_mission.web.controller;

import com.example.umc_week10_mission.converter.StoreConverter;
import com.example.umc_week10_mission.domain.Mission;
import com.example.umc_week10_mission.dto.StoreResponseDTO;
import com.example.umc_week10_mission.service.StoreService;
import com.example.umc_week10_mission.validation.annotation.CheckPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreService storeService;

    @Operation(summary = "특정 가게의 미션 목록 조회 API", description = "특정 가게의 미션들을 조회하는 API이며, 페이징을 포함합니다. query String으로 page 번호를 주세요.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK, 성공"),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다."),
            @Parameter(name = "page", description = "페이지 번호, 1번이 1 페이지 입니다.")
    })
    @GetMapping("/{storeId}/missions")
    public StoreResponseDTO.MissionListDTO getMissions(@PathVariable(name = "storeId") Long storeId,
                                                       @CheckPage @RequestParam(name = "page") Integer page) {
        // page - 1로 서비스에 전달 (Spring Data JPA는 0부터 시작)
        Page<Mission> missionPage = storeService.getMissions(storeId, page - 1);
        return StoreConverter.toMissionListDTO(missionPage);
    }
}