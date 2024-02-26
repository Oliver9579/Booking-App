package com.example.booking.email.controllers;

import com.example.booking.email.models.EmailVerificationDTO;
import com.example.booking.email.models.EmailVerificationToken;
import com.example.booking.email.services.EmailVerificationTokenService;
import com.example.booking.user.models.User;
import com.example.booking.user.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class EmailVerificationController {

  private EmailVerificationTokenService tokenService;
  private UserService userService;

  @GetMapping("/verify")
  public ResponseEntity<?> verify(@RequestParam String token) {
    EmailVerificationToken verificationToken = tokenService.getByTokenValue(token);
    User user = userService.verifyUser(verificationToken);
    return ResponseEntity.status(HttpStatus.OK).body(new EmailVerificationDTO(user.getUsername(), true));
  }

}
