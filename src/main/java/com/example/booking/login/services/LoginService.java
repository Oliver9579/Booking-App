package com.example.booking.login.services;

import com.example.booking.exceptions.UserNotFoundException;
import com.example.booking.exceptions.WrongPasswordException;
import com.example.booking.login.models.LoginDTO;
import com.example.booking.login.models.TokenDTO;

public interface LoginService {

  TokenDTO createTokenDtoResponse(LoginDTO loginDTO) throws UserNotFoundException, WrongPasswordException;

}
