package com.example.booking.exceptions;

public class NoFlightFoundException extends RuntimeException {

  public static final String MESSAGE = "There is no flight at the specified time or destination!";

  public NoFlightFoundException() {
    super(MESSAGE);
  }

}
