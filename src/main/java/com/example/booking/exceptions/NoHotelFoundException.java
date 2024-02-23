package com.example.booking.exceptions;

public class NoHotelFoundException extends RuntimeException {

  public static final String MESSAGE = "There is no hotel at the specified location";

  public NoHotelFoundException() {
    super(MESSAGE);
  }

}
