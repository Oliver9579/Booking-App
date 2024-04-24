package com.example.booking.user.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

  private int id;
  private String firstName;
  private String lastName;
  private String username;
  private String email;
  private String phoneNumber;
  private String dateOfBirth;
  private String nationality;
  private String gender;
  private String address;

}
