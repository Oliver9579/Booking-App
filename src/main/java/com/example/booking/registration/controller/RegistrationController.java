package com.example.booking.registration.controller;

import com.example.booking.registration.models.RegistrationDTO;
import com.example.booking.registration.models.RegistrationResponseDTO;
import com.example.booking.registration.services.RegistrationService;
import com.example.booking.user.models.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class RegistrationController {

  private RegistrationService registrationService;

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody @Valid RegistrationDTO rdto) {//todo a ? jelet kiszedni
    User user = registrationService.register(rdto);
    return ResponseEntity.status(201).body(new RegistrationResponseDTO(
            user.getId(), user.getUsername(), user.getEmail(), user.getPhoneNumber()));
  }

}
