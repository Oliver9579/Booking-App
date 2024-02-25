package com.example.booking.exceptions;

public class VerificationTokenNotFoundException extends RuntimeException {

  public static final String MESSAGE = "Verification token not found.";

  public VerificationTokenNotFoundException() {
    super(MESSAGE);
  }

}
