package com.example.booking.errorhandling;

import com.example.booking.exceptions.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class RestControllerExceptionHandlerTest {

  private RestControllerExceptionHandler restControllerExceptionHandler;
  private List<FieldError> fieldErrors;
  private String errorMessage;
  private String receivedMessage;
  private HttpStatus expectedStatus;
  private HttpStatus receivedStatus;
  private ResponseEntity<ErrorMessage> response;
  @Mock
  private BindingResult bindingResult;
  @Mock
  private FieldError fieldError;
  @Mock
  private FieldError fieldErrorTwo;
  @Mock
  private FieldError fieldErrorThree;
  @Mock
  private ExceptionHandlerUtility exceptionHandlerUtility;
  @Mock
  private MethodArgumentNotValidException methodArgumentNotValidException;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    restControllerExceptionHandler = new RestControllerExceptionHandler(exceptionHandlerUtility);
    fieldErrors = new ArrayList<>();
    fieldErrors.add(fieldError);
  }

  @Test
  public void handleMissingRequestFields_returnCorrectMessage_when_oneFieldIsMissing() {
    when(methodArgumentNotValidException.getBindingResult()).thenReturn(bindingResult);
    when(methodArgumentNotValidException.getBindingResult().getFieldErrors()).thenReturn(fieldErrors);
    when(fieldError.getCode()).thenReturn("NotBlank");
    when(exceptionHandlerUtility.createErrorMessageForMissingFields(fieldErrors))
            .thenReturn("Username is required.");

    response = restControllerExceptionHandler.handleMissingRequestFields(methodArgumentNotValidException);
    errorMessage = "Username is required.";
    receivedMessage = response.getBody().getMessage();
    expectedStatus = HttpStatus.BAD_REQUEST;
    receivedStatus = response.getStatusCode();

    assertEquals(errorMessage, receivedMessage);
    assertEquals(expectedStatus, receivedStatus);
  }

  @Test
  public void handleMissingRequestFields_returnCorrectMessage_when_twoFieldsAreMissing() {
    fieldErrors.add(fieldErrorTwo);
    when(methodArgumentNotValidException.getBindingResult()).thenReturn(bindingResult);
    when(methodArgumentNotValidException.getBindingResult().getFieldErrors()).thenReturn(fieldErrors);
    when(fieldError.getCode()).thenReturn("NotBlank");
    when(fieldErrorTwo.getCode()).thenReturn("NotBlank");
    when(exceptionHandlerUtility.createErrorMessageForMissingFields(fieldErrors))
            .thenReturn("Username and password are required.");

    response = restControllerExceptionHandler.handleMissingRequestFields(methodArgumentNotValidException);
    errorMessage = "Username and password are required.";
    receivedMessage = response.getBody().getMessage();
    expectedStatus = HttpStatus.BAD_REQUEST;
    receivedStatus = response.getStatusCode();

    assertEquals(errorMessage, receivedMessage);
    assertEquals(expectedStatus, receivedStatus);
  }

  @Test
  public void handleMissingRequestFields_returnCorrectMessage_when_multipleFieldsAreMissing() {
    fieldErrors.add(fieldErrorTwo);
    fieldErrors.add(fieldErrorThree);
    when(methodArgumentNotValidException.getBindingResult()).thenReturn(bindingResult);
    when(methodArgumentNotValidException.getBindingResult().getFieldErrors()).thenReturn(fieldErrors);
    when(fieldError.getCode()).thenReturn("NotBlank");
    when(fieldErrorTwo.getCode()).thenReturn("NotBlank");
    when(fieldErrorThree.getCode()).thenReturn("NotBlank");
    when(exceptionHandlerUtility.createErrorMessageForMissingFields(fieldErrors))
            .thenReturn("Username, password and email are required.");

    response = restControllerExceptionHandler.handleMissingRequestFields(methodArgumentNotValidException);
    errorMessage = "Username, password and email are required.";
    receivedMessage = response.getBody().getMessage();
    expectedStatus = HttpStatus.BAD_REQUEST;
    receivedStatus = response.getStatusCode();

    assertEquals(errorMessage, receivedMessage);
    assertEquals(expectedStatus, receivedStatus);
  }

  @Test
  public void handleMissingRequestFields_returnCorrectMessage_when_sizeConstraintValidationFails() {
    when(methodArgumentNotValidException.getBindingResult()).thenReturn(bindingResult);
    when(methodArgumentNotValidException.getBindingResult().getFieldErrors()).thenReturn(fieldErrors);
    when(fieldError.getCode()).thenReturn("Size");
    when(fieldError.getDefaultMessage()).thenReturn("Password must have 8 characters.");

    response = restControllerExceptionHandler.handleMissingRequestFields(methodArgumentNotValidException);
    errorMessage = "Password must have 8 characters.";
    receivedMessage = response.getBody().getMessage();
    expectedStatus = HttpStatus.BAD_REQUEST;
    receivedStatus = response.getStatusCode();

    assertEquals(errorMessage, receivedMessage);
    assertEquals(expectedStatus, receivedStatus);
  }

  @Test
  public void handleUserNotFound_returnCorrectMessage() {
    response = restControllerExceptionHandler.handleUserNotFound();
    errorMessage = UserNotFoundException.MESSAGE;
    receivedMessage = response.getBody().getMessage();
    expectedStatus = HttpStatus.UNAUTHORIZED;
    receivedStatus = response.getStatusCode();

    assertEquals(errorMessage, receivedMessage);
    assertEquals(expectedStatus, receivedStatus);
  }

  @Test
  public void handleLoginOrRegistrationIncorrect_should_returnCorrectMessage() {
    response = restControllerExceptionHandler.handleLoginOrRegistrationIncorrect();
    errorMessage = WrongPasswordException.MESSAGE;
    receivedMessage = response.getBody().getMessage();
    expectedStatus = HttpStatus.UNAUTHORIZED;
    receivedStatus = response.getStatusCode();

    assertEquals(errorMessage, receivedMessage);
    assertEquals(expectedStatus, receivedStatus);
  }

  @Test
  public void handleWhenAUniqueDataIsAlreadyTaken_should_returnCorrectMessage() {
    response = restControllerExceptionHandler.handleWhenAUniqueDataIsAlreadyTaken(new AlreadyTakenException(""));
    expectedStatus = HttpStatus.CONFLICT;
    receivedStatus = response.getStatusCode();

    assertEquals(expectedStatus, receivedStatus);
  }

  @Test
  public void handleWhenARequestDataIsMissing_should_returnCorrectMessage() {
    response = restControllerExceptionHandler.handleWhenARequestDataIsMissing(new MissingInputException(""));
    expectedStatus = HttpStatus.BAD_REQUEST;
    receivedStatus = response.getStatusCode();

    assertEquals(expectedStatus, receivedStatus);
  }

  @Test
  public void handleWhenARequestParamIsNull_should_returnCorrectMessage() {
    response = restControllerExceptionHandler.handleWhenARequestParamIsNull(new FormInputIsEmpty());
    expectedStatus = HttpStatus.BAD_REQUEST;
    receivedStatus = response.getStatusCode();

    assertEquals(expectedStatus, receivedStatus);
  }

  @Test
  public void handleIdNotFound_should_returnCorrectMessage() {
    response = restControllerExceptionHandler.handleIdNotFound();
    errorMessage = IdNotFoundException.MESSAGE;
    receivedMessage = response.getBody().getMessage();
    expectedStatus = HttpStatus.NOT_FOUND;
    receivedStatus = response.getStatusCode();

    assertEquals(errorMessage, receivedMessage);
    assertEquals(expectedStatus, receivedStatus);
  }

  @Test
  public void handleNoFlightAtTheSpecifiedTime_should_returnCorrectMessage() {
    response = restControllerExceptionHandler.handleNoFlightAtTheSpecifiedTime();
    errorMessage = NoFlightFoundException.MESSAGE;
    receivedMessage = response.getBody().getMessage();
    expectedStatus = HttpStatus.NOT_FOUND;
    receivedStatus = response.getStatusCode();

    assertEquals(errorMessage, receivedMessage);
    assertEquals(expectedStatus, receivedStatus);
  }

  @Test
  public void handleNoHotelAvailableAtTheGivenLocation_should_returnCorrectMessage() {
    response = restControllerExceptionHandler.handleNoHotelAvailableAtTheGivenLocation();
    errorMessage = NoHotelFoundException.MESSAGE;
    receivedMessage = response.getBody().getMessage();
    expectedStatus = HttpStatus.NOT_FOUND;
    receivedStatus = response.getStatusCode();

    assertEquals(errorMessage, receivedMessage);
    assertEquals(expectedStatus, receivedStatus);
  }

  @Test
  public void handleWhenTheCheckInDateIsSameAsTheCheckOutDate_should_returnCorrectMessage() {
    response = restControllerExceptionHandler.handleWhenTheCheckInDateIsSameAsTheCheckOutDate(new SameDateException());
    errorMessage = SameDateException.MESSAGE;
    receivedMessage = response.getBody().getMessage();
    expectedStatus = HttpStatus.BAD_REQUEST;
    receivedStatus = response.getStatusCode();

    assertEquals(errorMessage, receivedMessage);
    assertEquals(expectedStatus, receivedStatus);
  }

  @Test
  public void handleNoAutoFoundAtTheGivenLocation_should_returnCorrectMessage() {
    response = restControllerExceptionHandler.handleNoAutoFoundAtTheGivenLocation();
    errorMessage = NoCarFoundException.MESSAGE;
    receivedMessage = response.getBody().getMessage();
    expectedStatus = HttpStatus.NOT_FOUND;
    receivedStatus = response.getStatusCode();

    assertEquals(errorMessage, receivedMessage);
    assertEquals(expectedStatus, receivedStatus);
  }

  @Test
  public void handleNoAutoAvailableAtTheGivenTime_should_returnCorrectMessage() {
    response = restControllerExceptionHandler.handleNoAutoAvailableAtTheGivenTime();
    errorMessage = NoAvailableCarException.MESSAGE;
    receivedMessage = response.getBody().getMessage();
    expectedStatus = HttpStatus.BAD_REQUEST;
    receivedStatus = response.getStatusCode();

    assertEquals(errorMessage, receivedMessage);
    assertEquals(expectedStatus, receivedStatus);
  }

  @Test
  public void handleWhenAnAccountNotVerified_should_returnCorrectMessage() {
    response = restControllerExceptionHandler.handleWhenAnAccountNotVerified();
    errorMessage = UnverifiedAccountException.MESSAGE;
    receivedMessage = response.getBody().getMessage();
    expectedStatus = HttpStatus.FORBIDDEN;
    receivedStatus = response.getStatusCode();

    assertEquals(errorMessage, receivedMessage);
    assertEquals(expectedStatus, receivedStatus);
  }

  @Test
  public void handleWhenAVerificationTokenNotFound_should_returnCorrectMessage() {
    response = restControllerExceptionHandler.handleWhenAVerificationTokenNotFound();
    errorMessage = VerificationTokenNotFoundException.MESSAGE;
    receivedMessage = response.getBody().getMessage();
    expectedStatus = HttpStatus.NOT_FOUND;
    receivedStatus = response.getStatusCode();

    assertEquals(errorMessage, receivedMessage);
    assertEquals(expectedStatus, receivedStatus);
  }

  @Test
  public void handleNoBookingFound_should_returnCorrectMessage() {
    response = restControllerExceptionHandler.handleNoBookingFound();
    errorMessage = NoBookingFoundException.MESSAGE;
    receivedMessage = response.getBody().getMessage();
    expectedStatus = HttpStatus.NOT_FOUND;
    receivedStatus = response.getStatusCode();

    assertEquals(errorMessage, receivedMessage);
    assertEquals(expectedStatus, receivedStatus);
  }

}
