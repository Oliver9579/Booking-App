package com.example.booking.exceptions;

public class NoFlightException extends RuntimeException {

  public static final String MESSAGE = "There is no flight at the specified time or destination!";

  public NoFlightException() {
    super(MESSAGE);
  }

}
