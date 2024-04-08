package com.example.booking.car.DTOs;

import com.example.booking.car.models.CarType;
import com.example.booking.car.models.TransmissionType;
import com.example.booking.review.DTOs.ReviewResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllCarsDTO {

  private int id;
  private String brand;
  private String model;
  private CarType carType;
  private int capacity;
  private TransmissionType transmissionType;
  private String pickUpLocation;
  private String dropOffLocation;
  private int pricePerDay;
  private String img;
  private List<ReviewResponseDTO> reviews;
}
