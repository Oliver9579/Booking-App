package com.example.booking.car.DTOs;

import lombok.Data;

import java.util.List;

@Data
public class CarListDTO {

  private List<CarDTO> cars;

  public CarListDTO(List<CarDTO> cars) {
    this.cars = cars;
  }
}
