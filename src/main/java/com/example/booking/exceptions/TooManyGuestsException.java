package com.example.booking.exceptions;

public class TooManyGuestsException extends RuntimeException{

  public static final String MESSAGE = "We can't accommodate that many guests";

  public TooManyGuestsException() {
    super(MESSAGE);
  }

}
