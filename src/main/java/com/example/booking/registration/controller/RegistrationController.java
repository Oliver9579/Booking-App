package com.example.booking.registration.controller;

import com.example.booking.errorhandling.ErrorMessage;
import com.example.booking.registration.models.RegistrationDTO;
import com.example.booking.registration.models.RegistrationResponseDTO;
import com.example.booking.registration.services.RegistrationService;
import com.example.booking.user.models.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class RegistrationController {

  private RegistrationService registrationService;

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody @Valid RegistrationDTO rdto) {
    try {
      User user = registrationService.register(rdto);
      return ResponseEntity.status(201).body(new RegistrationResponseDTO(
              user.getId(), user.getUsername(), user.getEmail(), user.getPhoneNumber()));
    }catch (MessagingException e) {
      return ResponseEntity.status(500).body(new ErrorMessage("An error happened while sending your activation link."));
    }
  }

}
