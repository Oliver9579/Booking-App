package com.example.booking.registration.service;

import com.example.booking.registration.dtos.RegistrationDTO;
import com.example.booking.user.models.User;

public interface RegistrationService {

  void validateRegistration(RegistrationDTO rdto);

  User register(RegistrationDTO rdto);

}
