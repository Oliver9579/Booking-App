package com.example.booking.user.controllers;

import com.example.booking.user.models.NewUserDetailsRequestDTO;
import com.example.booking.user.models.User;
import com.example.booking.user.models.UserDTO;
import com.example.booking.user.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

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

  @PutMapping()
  public ResponseEntity<UserDTO> editUserDetails(UsernamePasswordAuthenticationToken auth,
                                                 @RequestBody NewUserDetailsRequestDTO newUserDetails) {
    int userId = ((User) auth.getPrincipal()).getId();
    User user = userService.getById(userId);
    return ResponseEntity.ok().body(userService.setNewUserDetails(user, newUserDetails));
  }

  @DeleteMapping()
  public ResponseEntity<UserDTO> deleteUser(UsernamePasswordAuthenticationToken auth) {
    int userId = ((User) auth.getPrincipal()).getId();
    User user = userService.getById(userId);
    return ResponseEntity.ok().body(userService.deleteUser(user));
  }


}
