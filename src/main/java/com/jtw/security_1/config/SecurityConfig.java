package com.jtw.security_1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception { //DSL 사용 보안구성
        return httpSecurity
                .httpBasic().disable() //UI, UX Disable
                .csrf().disable()//크로스 사이트 기능
                .cors().and() //도메인이 다를때 허용
                .authorizeHttpRequests()
                //인가 정책
                .requestMatchers("/api/v1/users/join", "/api/v1/users/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/reviews").authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //JWT를 쓰는 경우
                .and()
                .build();
    }
}
