package com.example.booking.review.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ReviewResponseDTO {

  private String username;
  private String comment;
  private Date reviewDate;

}
