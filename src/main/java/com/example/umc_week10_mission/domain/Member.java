package com.example.umc_week10_mission.domain;

import com.example.umc_week10_mission.domain.enums.Role;
import com.example.umc_week10_mission.domain.common.BaseEntity; // BaseEntity가 있다고 가정
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private LocalDate birth;

    // 주소 등의 추가 정보 필드
    private String address;
    private String detailAddress;

    // 성별 등 DTO에 있는 필드들 매핑 필요
    // private Gender gender;
}