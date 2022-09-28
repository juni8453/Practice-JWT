package com.cos.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        // 서버 응답 시 JSON 을 JS 에서 처리할 수 있게할지 설정
        // false 라면 JS 요청을 했을 때 응답을 보낼 수 없음.
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*"); // 모든 IP 에 응답 허용
        config.addAllowedHeader("*"); // 모든 Header 에 응답 허용
        config.addAllowedMethod("*"); // 모든 HTTP Method 에 응답 허용

        source.registerCorsConfiguration("/api/**", config);
        return new CorsFilter(source);
    }
}

