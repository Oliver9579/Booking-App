package com.example.booking.exceptions;

public class NoAvailableCarException extends RuntimeException {

  public static final String MESSAGE = "There is no cars available for the specified dates.";

  public NoAvailableCarException() {
    super(MESSAGE);
  }
}
