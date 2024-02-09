package com.example.booking.user.controllers;

import com.example.booking.user.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking/users")
@AllArgsConstructor
public class UserController {

  private UserService userService;


}
