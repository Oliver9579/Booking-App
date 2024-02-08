package com.example.booking.registration.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class RegistrationDTO {

  @NotBlank
  private String firstName;
  @NotBlank
  private String lastName;
  @NotBlank
  @Email(message = "Please provide a valid email address.", regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
  private String email;
  @NotBlank
  @Size(min = 8, message = "Password must have 8 characters.")
  private String password;
  @NotBlank
  private String phoneNumber;

  public RegistrationDTO(String firstName, String lastName, String email, String password, String phoneNumber) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.phoneNumber = phoneNumber;
  }

}
