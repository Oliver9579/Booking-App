package com.example.booking.exceptions;

public class NoRoomAvailableException extends RuntimeException {

  public static final String MESSAGE = "There is no available room at that specified time!";

  public NoRoomAvailableException() {
    super(MESSAGE);
  }
}
