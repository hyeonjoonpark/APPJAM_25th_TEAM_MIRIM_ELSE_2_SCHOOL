package org.appjam.project.domain.auth.presentation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

  private String role;
  private String name;
  private String username;

  public void setUserDto(String role, String username) {
    this.role = role;
    this.username = username;
  }
}
