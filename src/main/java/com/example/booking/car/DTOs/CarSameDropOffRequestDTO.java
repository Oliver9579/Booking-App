package com.example.booking.car.DTOs;

public class CarSameDropOffRequestDTO extends CarRequestDTO {

  public CarSameDropOffRequestDTO(String pickUpLocation, String pickUpDate, String dropOffDate) {
    super(pickUpLocation, pickUpDate, dropOffDate);
  }

  public static CarSameDropOffRequestDTO convertCarRequest(String pickUpLocation, String pickUpDate, String dropOffDate) {
    return new CarSameDropOffRequestDTO(pickUpLocation, pickUpDate, dropOffDate);
  }

}
