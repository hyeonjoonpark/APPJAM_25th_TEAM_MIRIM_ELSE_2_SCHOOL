package org.appjam.project.domain.auth.presentation.dto.response;

import lombok.RequiredArgsConstructor;
import org.appjam.project.domain.auth.presentation.dto.UserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {
  private final OAuth2Response oAuth2Response;
  private final String role;


  public CustomOAuth2User(UserDTO userDto, OAuth2Response oAuth2Response, String role) {
      this.oAuth2Response = oAuth2Response;
      this.role = role;
  }

  @Override
  public Map<String, Object> getAttributes() {
    return null;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Collection<GrantedAuthority> collection = new ArrayList<>();

    collection.add((GrantedAuthority) () -> role);

    return collection;
  }

  @Override
  public String getName() {

    return oAuth2Response.getName();
  }
}
