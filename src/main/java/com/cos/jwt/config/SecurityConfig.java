package com.cos.jwt.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CorsFilter corsFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();

        // 세션 로그인 방식을 쓰지 않겠다고 선언한 것
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilter(corsFilter) // Global CORS 설정
            .formLogin().disable() // Form 로그인 사용 X

            // headers Authorization(id, password) 를 담아 계속해서 인증을 받는 방식
            // id, password 가 암호화가 되질 않기 때문에 노출 위험이 있다. -> https 사용해야함
            // httpBasic 방식이 아닌 JWT 를 사용하면 headers Authorization(Bearer Token) 을 담아 인증을 받는 방식을 사용한다.
            // 노출이 된다고 해도 토큰 자체가 ID, Password 가 아니고 토큰은 유효시간이 끝나면 다시 만들어지니 위험부담이 적다.
            .httpBasic().disable()
            .authorizeRequests()

            .antMatchers("/api/v1/user/**")
            .access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGEr') or hasRole('ROLE_ADMIN')")

            .antMatchers("/api/v1/manager/**")
            .access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")

            .antMatchers("/api/v1/admin/**")
            .access("hasRole('ROLE_ADMIN')")
            .anyRequest().permitAll();

        return httpSecurity.build();
    }
}
