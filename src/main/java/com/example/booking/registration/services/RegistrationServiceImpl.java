package com.example.booking.registration.services;

import com.example.booking.email.models.EmailVerificationToken;
import com.example.booking.email.services.EmailService;
import com.example.booking.exceptions.AlreadyTakenException;
import com.example.booking.exceptions.UserNotFoundException;
import com.example.booking.registration.models.RegistrationDTO;
import com.example.booking.security.password.PasswordService;
import com.example.booking.user.models.User;
import com.example.booking.user.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

  private UserService userService;
  private PasswordService passwordService;
  private EmailService emailService;

  public void validateRegistration(RegistrationDTO rdto) {
    try {
      userService.getByUsername(rdto.getUserName());
      throw new AlreadyTakenException("This username is already registered!");
    } catch (UserNotFoundException e) {
    }
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

  public User register(RegistrationDTO rdto) throws MessagingException {
    validateRegistration(rdto);
    User user = userService.convertRegisterDTOToUser(rdto);
    user.setVerificationToken(new EmailVerificationToken(user));
    MimeMessage verificationMessage = emailService.createVerificationMail(user);
    emailService.sendMail(verificationMessage);
    return userService.save(user);
  }
}
