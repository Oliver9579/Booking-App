package com.example.booking.user.controllers;

import com.example.booking.user.models.User;
import com.example.booking.user.models.UserDTO;
import com.example.booking.user.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

  private UserService userService;

  @GetMapping()
  public ResponseEntity<UserDTO> getUser(UsernamePasswordAuthenticationToken auth) {
    int userId = ((User) auth.getPrincipal()).getId();
    User user = userService.getById(userId);
    return ResponseEntity.ok().body(userService.convertUserToDTO(user));
  }

}
