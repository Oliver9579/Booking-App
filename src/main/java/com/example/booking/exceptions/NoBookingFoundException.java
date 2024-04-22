package com.example.booking.exceptions;

public class NoBookingFoundException extends RuntimeException {

  public static final String MESSAGE = "No bookings found.";

  public NoBookingFoundException() {
    super(MESSAGE);
  }
}
