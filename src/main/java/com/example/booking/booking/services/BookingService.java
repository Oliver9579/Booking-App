package com.example.booking.booking.services;

import com.example.booking.booking.DTOs.bookingFlight.BookingOneWayFlightRequestDTO;
import com.example.booking.booking.DTOs.bookingFlight.BookingOneWayFlightResponseDTO;
import com.example.booking.user.models.User;

public interface BookingService{


  BookingOneWayFlightResponseDTO createOneWayFlightBooking(User user, BookingOneWayFlightRequestDTO bookingFlight);
}
