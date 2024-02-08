package com.example.booking.login.controllers;

import com.example.booking.login.models.LoginDTO;
import com.example.booking.login.services.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class LoginController {

  private LoginService loginService;

  @PostMapping("/login")
  @ResponseBody
  public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDTO) {
    return ResponseEntity.ok(loginService.createTokenDtoResponse(loginDTO));
  }

}
