package com.example.booking.exceptions;

public class NoCarFoundException extends RuntimeException {

  public static final String MESSAGE = "There is no cars found with the specified drop-off location.";

  public NoCarFoundException() {
    super(MESSAGE);
  }
}
