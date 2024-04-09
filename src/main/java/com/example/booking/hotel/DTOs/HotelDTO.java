package com.example.booking.hotel.DTOs;

import com.example.booking.review.DTOs.ReviewResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class HotelDTO {

  private Integer id;
  private String name;
  private String location;
  private int stars;
  private List<ReviewResponseDTO> reviews;

}
