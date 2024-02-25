package com.example.booking.registration.services;

import com.example.booking.registration.models.RegistrationDTO;
import com.example.booking.user.models.User;

import javax.mail.MessagingException;

public interface RegistrationService {

  void validateRegistration(RegistrationDTO rdto);

  User register(RegistrationDTO rdto) throws MessagingException;

}
