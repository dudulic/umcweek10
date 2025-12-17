package com.example.umc_week10_mission.web.controller;

import com.example.umc_week10_mission.api.ApiResponse;
import com.example.umc_week10_mission.converter.MemberConverter;
import com.example.umc_week10_mission.domain.Review;
import com.example.umc_week10_mission.domain.mapping.MemberMission;
import com.example.umc_week10_mission.dto.MemberReqDTO;
import com.example.umc_week10_mission.dto.MemberResponseDTO;
import com.example.umc_week10_mission.service.MemberCommandService;
import com.example.umc_week10_mission.service.MemberQueryService;
import com.example.umc_week10_mission.service.MemberService;
import com.example.umc_week10_mission.validation.annotation.CheckPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import jakarta.validation.Valid;
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
    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService; // 추가

    @PostMapping("/sign-up")
    public ApiResponse<MemberResponseDTO.JoinResultDTO> signup(@RequestBody @Valid MemberReqDTO.JoinDTO request) {
        return ApiResponse.onSuccess(memberCommandService.signup(request));
    }

    // 로그인 API 추가
    @PostMapping("/login")
    public ApiResponse<MemberResponseDTO.LoginDTO> login(@RequestBody @Valid MemberReqDTO.LoginDTO request) {
        return ApiResponse.onSuccess(memberQueryService.login(request));
    }

    @Operation(summary = "내가 작성한 리뷰 목록 조회 API")
    @Parameters({ @Parameter(name = "memberId"), @Parameter(name = "page") })
    @GetMapping("/{memberId}/reviews")
    public ApiResponse<MemberResponseDTO.ReviewListDTO> getMyReviews(@PathVariable(name = "memberId") Long memberId,
                                                                     @CheckPage @RequestParam(name = "page") Integer page) {
        Page<Review> reviewPage = memberService.getMyReviews(memberId, page - 1);
        return ApiResponse.onSuccess(MemberConverter.toReviewListDTO(reviewPage));
    }

    @Operation(summary = "내가 진행중인 미션 목록 조회 API")
    @Parameters({ @Parameter(name = "memberId"), @Parameter(name = "page") })
    @GetMapping("/{memberId}/missions/challenging")
    public ApiResponse<MemberResponseDTO.MyMissionListDTO> getMyChallengingMissions(@PathVariable(name = "memberId") Long memberId,
                                                                                    @CheckPage @RequestParam(name = "page") Integer page) {
        Page<MemberMission> missionPage = memberService.getMyChallengingMissions(memberId, page - 1);
        return ApiResponse.onSuccess(MemberConverter.toMyMissionListDTO(missionPage));
    }
}