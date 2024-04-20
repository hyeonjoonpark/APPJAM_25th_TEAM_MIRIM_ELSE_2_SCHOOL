package org.appjam.project.domain.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.appjam.project.domain.auth.presentation.dto.UserDTO;
import org.appjam.project.domain.auth.presentation.dto.response.CustomOAuth2User;
import org.appjam.project.domain.auth.presentation.dto.response.OAuth2Response;
import org.appjam.project.domain.auth.presentation.dto.response.google.GoogleResponse;
import org.appjam.project.domain.user.User;
import org.appjam.project.domain.user.repository.UserRepository;
import org.appjam.project.domain.user.types.RoleType;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
  private final UserRepository userRepository;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    OAuth2User oAuth2User = super.loadUser(userRequest);
    System.out.println("oAuth2User = " + oAuth2User.getAttributes());
    log.info("oAuth2User = {}", oAuth2User);

    String registrationId = userRequest.getClientRegistration().getRegistrationId();

    OAuth2Response oAuth2Response = null;

    if (registrationId.equals("google")) {
      oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
    }

    // DB 저장
    String userName = oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId();
    User userData = userRepository.findByUsername(userName);

    String role = null;

    if (userData == null) {
      User user = User.builder()
        .username(userName)
        .email(oAuth2Response.getEmail())
        .role(RoleType.ROLE_USER)
        .build();

      userRepository.save(user);
    } else {
      role = String.valueOf(userData.getRole());
      userData.setEmail(oAuth2Response.getEmail());
      userRepository.save(userData);

      // 로그인 성공 시 accessToken 리턴
      return oAuth2User;
    }

    UserDTO userDto = new UserDTO();
    return new CustomOAuth2User(userDto, oAuth2Response, role);
  }
}
