package com.example.booking.login.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class LoginDTO {

  @NotBlank
  private String userName;
  @NotBlank
  @Size(min = 8, message = "Password must have 8 characters.")
  private String password;

  public LoginDTO(String userName, String password) {
    this.userName = userName;
    this.password = password;
  }

}
