package com.example.booking.exceptions;

public class NotEnoughRoomAvailableException extends RuntimeException{

  public static final String MESSAGE = "There is not enough available room!";

  public NotEnoughRoomAvailableException() {
    super(MESSAGE);
  }

}
