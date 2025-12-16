package com.example.umc_week10_mission.web.controller;

import com.example.umc_week10_mission.converter.MemberConverter;
import com.example.umc_week10_mission.domain.Review;
import com.example.umc_week10_mission.domain.mapping.MemberMission;
import com.example.umc_week10_mission.dto.MemberResponseDTO;
import com.example.umc_week10_mission.service.MemberService;
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
@RequestMapping("/members")
public class MemberRestController {

    private final MemberService memberService;

    @Operation(summary = "내가 작성한 리뷰 목록 조회 API", description = "내가 작성한 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다.")
    @ApiResponses({ @ApiResponse(responseCode = "200", description = "OK, 성공") })
    @Parameters({ @Parameter(name = "memberId", description = "회원 아이디"), @Parameter(name = "page", description = "페이지 번호") })
    @GetMapping("/{memberId}/reviews")
    public MemberResponseDTO.ReviewListDTO getMyReviews(@PathVariable(name = "memberId") Long memberId,
                                                        @CheckPage @RequestParam(name = "page") Integer page) {
        Page<Review> reviewPage = memberService.getMyReviews(memberId, page - 1);
        return MemberConverter.toReviewListDTO(reviewPage);
    }

    @Operation(summary = "내가 진행중인 미션 목록 조회 API", description = "내가 현재 진행중인(CHALLENGING) 미션 목록을 조회합니다.")
    @ApiResponses({ @ApiResponse(responseCode = "200", description = "OK, 성공") })
    @Parameters({ @Parameter(name = "memberId", description = "회원 아이디"), @Parameter(name = "page", description = "페이지 번호") })
    @GetMapping("/{memberId}/missions/challenging")
    public MemberResponseDTO.MyMissionListDTO getMyChallengingMissions(@PathVariable(name = "memberId") Long memberId,
                                                                       @CheckPage @RequestParam(name = "page") Integer page) {
        Page<MemberMission> missionPage = memberService.getMyChallengingMissions(memberId, page - 1);
        return MemberConverter.toMyMissionListDTO(missionPage);
    }
}