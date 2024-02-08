package com.example.booking.registration.services;

import com.example.booking.exceptions.AlreadyTakenException;
import com.example.booking.exceptions.UserNotFoundException;
import com.example.booking.registration.models.RegistrationDTO;
import com.example.booking.security.password.PasswordService;
import com.example.booking.user.models.User;
import com.example.booking.user.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

  private UserService userService;
  private PasswordService passwordService;

  public void validateRegistration(RegistrationDTO rdto) {
    try {
      userService.getByEmail(rdto.getEmail());
      throw new AlreadyTakenException("This email is already registered!");
    } catch (UserNotFoundException e) {
    }
    try {
      userService.getByPhoneNumber(rdto.getPhoneNumber());
      throw new AlreadyTakenException("This phone number is already registered!");
    } catch (UserNotFoundException e) {
    }
  }

  public User register(RegistrationDTO rdto) {
    validateRegistration(rdto);
    return userService.save(new User(rdto.getFirstName(), rdto.getLastName(), rdto.getEmail(),
            passwordService.passwordEncoding(rdto.getPassword()), rdto.getPhoneNumber()));
  }
}
