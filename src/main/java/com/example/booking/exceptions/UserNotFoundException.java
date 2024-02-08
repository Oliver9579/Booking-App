package com.example.booking.exceptions;

import com.example.booking.errorhandling.ErrorMessage;

public class UserNotFoundException extends RuntimeException {

  public static final String MESSAGE = "No such user.";

  public UserNotFoundException() {
    super(MESSAGE);
  }
}
