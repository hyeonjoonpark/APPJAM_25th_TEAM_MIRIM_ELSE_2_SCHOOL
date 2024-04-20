package org.appjam.project.domain.auth.utils.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.appjam.project.domain.auth.presentation.dto.UserDTO;
import org.appjam.project.domain.auth.presentation.dto.response.CustomOAuth2User;
import org.appjam.project.domain.auth.presentation.dto.response.OAuth2Response;
import org.appjam.project.domain.auth.utils.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    //cookie들을 불러온 뒤 Authorization Key에 담긴 쿠키를 찾음
    String authorization = null;
    Cookie[] cookies = request.getCookies();
    for (Cookie cookie : cookies) {

      System.out.println(cookie.getName());
      if (cookie.getName().equals("Authorization")) {

        authorization = cookie.getValue();
      }
    }

    //Authorization 헤더 검증
    if (authorization == null) {

      System.out.println("token null");
      filterChain.doFilter(request, response);

      //조건이 해당되면 메소드 종료 (필수)
      return;
    }

    //토큰
    String token = authorization;

    //토큰 소멸 시간 검증
    if (JwtUtil.isExpired(token)) {

      System.out.println("token expired");
      filterChain.doFilter(request, response);

      //조건이 해당되면 메소드 종료 (필수)
      return;
    }

    //토큰에서 username과 role 획득
    String username = JwtUtil.getUsername(token);
    String role = JwtUtil.getRole(token);

    //userDTO를 생성하여 값 set
    UserDTO userDto = new UserDTO();
    userDto.setUserDto(role, username);

    OAuth2Response oAuth2Response = null;

    //UserDetails에 회원 정보 객체 담기
    CustomOAuth2User customOAuth2User = new CustomOAuth2User(userDto, oAuth2Response, role);

    //스프링 시큐리티 인증 토큰 생성
    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(customOAuth2User, null, customOAuth2User.getAuthorities());
    //세션에 사용자 등록
    SecurityContextHolder.getContext().setAuthentication(authToken);

    filterChain.doFilter(request, response);
  }
}
