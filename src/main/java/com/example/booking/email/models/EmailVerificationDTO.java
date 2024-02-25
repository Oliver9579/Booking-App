package com.example.booking.email.models;

import lombok.Data;

@Data
public class EmailVerificationDTO {

  private String username;
  private Boolean enabled;

  public EmailVerificationDTO(String username, Boolean enabled) {
    this.username = username;
    this.enabled = enabled;
  }

}
