package com.example.umc_week10_mission.validation.validator;

import com.example.umc_week10_mission.validation.annotation.CheckPage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CheckPageValidator implements ConstraintValidator<CheckPage, Integer> {
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) return true; // null 처리는 @NotNull 등으로 별도 처리
        return value >= 1; // 0 이하는 에러 처리
    }
}