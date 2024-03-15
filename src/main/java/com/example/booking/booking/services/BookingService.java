package com.example.booking.booking.services;

import com.example.booking.booking.DTOs.bookingCar.BookingCarRequestDTO;
import com.example.booking.booking.DTOs.bookingCar.BookingCarResponseDTO;
import com.example.booking.booking.DTOs.bookingFlight.BookingOneWayFlightRequestDTO;
import com.example.booking.booking.DTOs.bookingFlight.BookingOneWayFlightResponseDTO;
import com.example.booking.booking.DTOs.bookingFlight.BookingRoundTripFlightRequestDTO;
import com.example.booking.booking.DTOs.bookingFlight.BookingRoundTripFlightResponseDTO;
import com.example.booking.booking.DTOs.bookingHotel.BookingHotelRequestDTO;
import com.example.booking.booking.DTOs.bookingHotel.BookingHotelResponseDTO;
import com.example.booking.user.models.User;

public interface BookingService {


  BookingOneWayFlightResponseDTO createOneWayFlightBooking(User user, BookingOneWayFlightRequestDTO bookingFlight);

  BookingRoundTripFlightResponseDTO createRoundTripFlightBooking(User user, BookingRoundTripFlightRequestDTO bookingFlight);

  BookingHotelResponseDTO createHotelBooking(User user, BookingHotelRequestDTO bookingHotel);

  BookingCarResponseDTO createCarBooking(User user, BookingCarRequestDTO bookingCar);
}
