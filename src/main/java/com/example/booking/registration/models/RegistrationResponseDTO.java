package com.example.booking.registration.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegistrationResponseDTO {

  private int id;
  private String userName;
  private String email;
  private String phoneNumber;

  public RegistrationResponseDTO(int id, String userName, String email, String phoneNumber) {
    this.id = id;
    this.userName = userName;
    this.email = email;
    this.phoneNumber = phoneNumber;
  }
}
