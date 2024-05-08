package com.example.booking.errorhandling;

import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ExceptionHandlerUtilityTest {

  private ExceptionHandlerUtility exceptionHandlerUtility;
  private List<FieldError> fieldErrors;

  @Before
  public void setup() {
    exceptionHandlerUtility = new ExceptionHandlerUtility();
    fieldErrors = new ArrayList<>();
  }

  @Test
  public void testCreateErrorMessageForSingleMissingFieldWithNullIsGiven() {
    assertEquals("", exceptionHandlerUtility.createErrorMessageForMissingFields(null));
  }

  @Test
  public void testCreateErrorMessageForSingleMissingFieldWithEmptyListIsGiven() {
    assertEquals("", exceptionHandlerUtility.createErrorMessageForMissingFields(fieldErrors));
  }

  @Test
  public void testCreateErrorMessageForMissingFieldWithListContainsOneFieldError() {
    fieldErrors.add(new FieldError("objectName", "username", "defaultMessage"));
    assertEquals("Username is required.",
            exceptionHandlerUtility.createErrorMessageForMissingFields(fieldErrors));
  }

  @Test
  public void testCreateErrorMessageForMissingFieldsWithListContainsMultipleFields() {
    fieldErrors.add(new FieldError("objectName", "username", "defaultMessage"));
    fieldErrors.add(new FieldError("objectName", "password", "defaultMessage"));
    fieldErrors.add(new FieldError("objectName", "email", "defaultMessage"));
    assertEquals("Username, password and email are required.",
            exceptionHandlerUtility.createErrorMessageForMissingFields(fieldErrors));
  }

}
