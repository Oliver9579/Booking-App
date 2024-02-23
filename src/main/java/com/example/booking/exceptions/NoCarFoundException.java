package com.example.booking.exceptions;

public class NoCarFoundException extends RuntimeException {

  public static final String MESSAGE = "There is no cars found with the specified data.";

  public NoCarFoundException() {
    super(MESSAGE);
  }
}
