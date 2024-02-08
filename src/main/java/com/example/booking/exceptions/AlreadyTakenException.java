package com.example.booking.exceptions;

public class AlreadyTakenException extends RuntimeException{

  public AlreadyTakenException(String message) {
    super(message);
  }

}
