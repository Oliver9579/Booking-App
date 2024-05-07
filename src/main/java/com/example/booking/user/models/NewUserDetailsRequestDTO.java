package com.example.booking.user.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NewUserDetailsRequestDTO {

  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;
  private String dateOfBirth;
  private String nationality;
  private String gender;
  private String address;

}
