package com.example.booking.exceptions;

public class FormInputIsEmpty extends RuntimeException {

  public static final String MESSAGE = "Please enter all the details!";

  public FormInputIsEmpty() {
    super(MESSAGE);
  }
}