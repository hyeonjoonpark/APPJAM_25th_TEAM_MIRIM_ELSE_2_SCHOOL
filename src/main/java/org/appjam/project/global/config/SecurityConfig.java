package org.appjam.project.global.config;

import lombok.RequiredArgsConstructor;
import org.appjam.project.domain.auth.service.CustomOAuth2UserService;
import org.appjam.project.domain.auth.utils.JwtUtil;
import org.appjam.project.domain.auth.utils.handler.CustomSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final CustomOAuth2UserService customOAuth2UserService;
  private final CustomSuccessHandler customSuccessHandler;
  private final JwtUtil jwtUtil;


  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    //csrf disable
    http
      .csrf(AbstractHttpConfigurer::disable);

    //From 로그인 방식 disable
    http
      .formLogin(AbstractHttpConfigurer::disable);

    //HTTP Basic 인증 방식 disable
    http
      .httpBasic(AbstractHttpConfigurer::disable);

    //oauth2
    http
      .oauth2Login((oauth2) -> oauth2
        .userInfoEndpoint((userInfoEndpointConfig) -> userInfoEndpointConfig
          .userService(customOAuth2UserService))
        .successHandler(customSuccessHandler)
      );

    //경로별 인가 작업
    http
      .authorizeHttpRequests((auth) -> auth
        .requestMatchers("/").permitAll()
        .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
        .anyRequest().permitAll());

    //세션 설정 : STATELESS
    http
      .sessionManagement((session) -> session
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    return http.build();
  }
}
