package com.example.booking.exceptions;

public class WrongPasswordException extends RuntimeException{

  public static final String MESSAGE = "The password is incorrect!";

  public WrongPasswordException() {
    super(MESSAGE);
  }
}
