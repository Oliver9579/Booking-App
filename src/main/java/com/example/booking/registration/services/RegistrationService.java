package com.example.booking.registration.services;

import com.example.booking.registration.models.RegistrationDTO;
import com.example.booking.user.models.User;

public interface RegistrationService {

  void validateRegistration(RegistrationDTO rdto);

  User register(RegistrationDTO rdto);

}
