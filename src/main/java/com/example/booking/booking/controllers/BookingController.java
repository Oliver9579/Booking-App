package com.example.booking.booking.controllers;

import com.example.booking.booking.services.BookingService;
import com.example.booking.user.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/bookings")
public class BookingController {

  private BookingService bookingService;
  private UserService userService;

}
