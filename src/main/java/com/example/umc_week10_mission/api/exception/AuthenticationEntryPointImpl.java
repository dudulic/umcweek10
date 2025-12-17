package com.example.umc_week10_mission.api.exception;

import com.example.umc_week10_mission.api.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        // GeneralErrorCode.UNAUTHORIZED 대신 하드코딩된 예시 코드 사용 (실제 구현시 ErrorCode Enum 사용 권장)
        ApiResponse<Void> errorResponse = ApiResponse.onFailure("COMMON401", "인증이 필요합니다.", null);

        objectMapper.writeValue(response.getOutputStream(), errorResponse);
    }
}