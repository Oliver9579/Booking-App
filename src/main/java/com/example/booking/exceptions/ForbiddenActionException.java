package com.example.booking.exceptions;

public class ForbiddenActionException extends RuntimeException {

  public static final String MESSAGE = "Forbidden action";

  public ForbiddenActionException() {
    super(MESSAGE);
  }
}
