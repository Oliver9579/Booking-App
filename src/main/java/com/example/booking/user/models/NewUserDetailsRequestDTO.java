package com.example.booking.user.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class NewUserDetailsRequestDTO {

  @NotBlank
  private String firstName;
  @NotBlank
  private String lastName;
  @NotBlank
  @Email(message = "Please provide a valid email address.", regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
  private String email;
  @NotBlank
  private String phoneNumber;
  @NotNull
  private String dateOfBirth;
  @NotBlank
  private String nationality;
  @NotBlank
  private String gender;
  @NotBlank
  private String address;

}
