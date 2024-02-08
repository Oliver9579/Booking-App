package com.example.booking.exceptions;

public class MissingInputException extends RuntimeException {

  public MissingInputException(String message) {
    super(message);
  }

}
