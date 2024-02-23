package com.example.booking.exceptions;

public class SameDateException extends RuntimeException{

  public static final String MESSAGE = "The two given dates is same!";

  public SameDateException() {
    super(MESSAGE);
  }
}
