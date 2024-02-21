package com.example.booking.errorhandling;

import com.example.booking.exceptions.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@AllArgsConstructor
public class RestControllerExceptionHandler {

  private ExceptionHandlerUtility exceptionHandlerUtility;

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorMessage> handleMissingRequestFields(MethodArgumentNotValidException e) {
    List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
    List<FieldError> missingErrors = fieldErrors.stream()
            .filter(error -> error.getCode().equals("NotNull") || error.getCode().equals("NotBlank"))
            .collect(Collectors.toList());
    String message = fieldErrors.get(0).getDefaultMessage();
    if (!missingErrors.isEmpty()) {
      message = exceptionHandlerUtility.createErrorMessageForMissingFields(missingErrors);
    }
    return ResponseEntity.badRequest().body(new ErrorMessage(message));
  }

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<ErrorMessage> handleUserNotFound() {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorMessage(UserNotFoundException.MESSAGE));
  }

  @ExceptionHandler(AlreadyTakenException.class)
  public ResponseEntity<ErrorMessage> handleWhenAUniqueDataIsAlreadyTaken(AlreadyTakenException e) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorMessage(e.getMessage()));
  }

  @ExceptionHandler(MissingInputException.class)
  public ResponseEntity<ErrorMessage> handleWhenARequestDataIsMissing(MissingInputException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(e.getMessage()));
  }

  @ExceptionHandler(IdNotFoundException.class)
  public ResponseEntity<ErrorMessage> handleIdNotFound() {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(IdNotFoundException.MESSAGE));
  }

  @ExceptionHandler(NoFlightException.class)
  public ResponseEntity<ErrorMessage> handleNoFlightAtTheSpecifiedTime() {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(NoFlightException.MESSAGE));
  }

  @ExceptionHandler(NoHotelException.class)
  public ResponseEntity<ErrorMessage> handleNoHotelAtTheGivenLocation() {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(NoHotelException.MESSAGE));
  }

  @ExceptionHandler(NoRoomAvailableException.class)
  public ResponseEntity<ErrorMessage> handleWhenZeroRoomIsAvailable() {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(NoRoomAvailableException.MESSAGE));
  }

  @ExceptionHandler(NotEnoughRoomAvailableException.class)
  public ResponseEntity<ErrorMessage> handleNotEnoughRoomAvailable() {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(NotEnoughRoomAvailableException.MESSAGE));
  }

  @ExceptionHandler(TooManyGuestsException.class)
  public ResponseEntity<ErrorMessage> handleWhenTheGivenGuestNumberToMuch() {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(TooManyGuestsException.MESSAGE));
  }

  @ExceptionHandler(SameDateException.class)
  public ResponseEntity<ErrorMessage> handleWhenTheCheckInDateIsSameAsTheCheckOutDate(SameDateException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(e.getMessage()));
  }

}
