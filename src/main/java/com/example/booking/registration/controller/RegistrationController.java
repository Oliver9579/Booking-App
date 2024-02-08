package com.example.booking.registration.controller;

import com.example.booking.registration.dtos.RegistrationDTO;
import com.example.booking.registration.dtos.RegistrationResponseDTO;
import com.example.booking.registration.service.RegistrationService;
import com.example.booking.user.models.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class RegistrationController {

  private RegistrationService registrationService;

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody @Valid RegistrationDTO rdto) {
    User user = registrationService.register(rdto);
    return ResponseEntity.status(201).body(new RegistrationResponseDTO(
            user.getId(), user.getUsername(), user.getEmail(), user.getPhoneNumber()));
  }

}
