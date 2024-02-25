package com.example.booking.exceptions;

public class UnverifiedAccountException extends RuntimeException {

  public static final String MESSAGE = "Please activate your account before attempting to log in.";

  public UnverifiedAccountException() {
    super(MESSAGE);
  }

}
