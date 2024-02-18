package com.example.booking.exceptions;

public class NoHotelException extends RuntimeException {

  public static final String MESSAGE = "There is no hotel at the specified location";

  public NoHotelException() {
    super(MESSAGE);
  }

}
