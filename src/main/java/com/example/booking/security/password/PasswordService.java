package com.example.booking.security.password;

public interface PasswordService {

  String passwordEncoding(String password);

  boolean isPasswordMatch(String password, String encodedPassword);

}
