package com.example.booking.exceptions;

public class SameDateException extends RuntimeException{

  public static final String MESSAGE = "The check-in date is the same as the check-out!";

  public SameDateException() {
    super(MESSAGE);
  }
}
