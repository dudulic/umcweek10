//package com.example.umc_week10_mission.config;
//
//import com.example.umc_week10_mission.api.exception.AuthenticationEntryPointImpl;
//import com.example.umc_week10_mission.service.auth.CustomUserDetailsService;
//import com.example.umc_week10_mission.service.auth.JwtAuthFilter;
//import com.example.umc_week10_mission.service.auth.JwtUtil;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@EnableWebSecurity
//@Configuration
//@RequiredArgsConstructor
//public class SecurityConfig {
//
//    private final JwtUtil jwtUtil;
//    private final CustomUserDetailsService customUserDetailsService;
//    private final AuthenticationEntryPointImpl authenticationEntryPoint;
//
//    private final String[] allowUris = {
//            "/",
//            "/error",
//            "/swagger-ui/**",
//            "/swagger-resources/**",
//            "/v3/api-docs/**",
//            "/members/sign-up",
//            "/members/login", // 로그인 경로 허용 추가
//            "/login"
//    };
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(requests -> requests
//                        .requestMatchers(allowUris).permitAll()
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        .anyRequest().authenticated()
//                )
//                .formLogin(AbstractHttpConfigurer::disable) // 폼 로그인 비활성화
//                .csrf(AbstractHttpConfigurer::disable)
//                .addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class) // 필터 추가
//                .exceptionHandling(handler -> handler.authenticationEntryPoint(authenticationEntryPoint)) // 예외 처리 추가
//                .logout(logout -> logout
//                        .logoutUrl("/logout")
//                        .logoutSuccessUrl("/login?logout")
//                        .permitAll()
//                );
//
//        return http.build();
//    }
//
//    @Bean
//    public JwtAuthFilter jwtAuthFilter() {
//        return new JwtAuthFilter(jwtUtil, customUserDetailsService);
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}