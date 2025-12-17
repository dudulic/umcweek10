package com.example.umc_week10_mission.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class MemberReqDTO {

    public record JoinDTO(
            @NotBlank String name,
            @Email String email,
            @NotBlank String password,
            @NotNull LocalDate birth,
            @NotBlank String address,
            @NotBlank String specAddress
    ){}

    // 로그인 요청 DTO 추가
    public record LoginDTO(
            @NotBlank String email,
            @NotBlank String password
    ){}
}