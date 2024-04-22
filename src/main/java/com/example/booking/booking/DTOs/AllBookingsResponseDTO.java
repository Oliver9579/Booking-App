package com.example.booking.booking.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AllBookingsResponseDTO {

  List<BookingResponseDTO> bookings;

}
