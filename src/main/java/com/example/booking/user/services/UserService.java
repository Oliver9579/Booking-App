package com.example.booking.user.services;

import com.example.booking.booking.models.Booking;
import com.example.booking.email.models.EmailVerificationToken;
import com.example.booking.registration.models.RegistrationDTO;
import com.example.booking.user.models.NewUserDetailsRequestDTO;
import com.example.booking.user.models.User;
import com.example.booking.user.models.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

  User getByEmail(String email);

  User getByPhoneNumber(String phoneNumber);

  User getByUsername(String userName);

  User getById(int id);

  User save(User user);

  UserDTO convertUserToDTO(User user);

  User convertRegisterDTOToUser(RegistrationDTO rdto);

  User verifyUser(EmailVerificationToken token);

  Boolean isUserIdMatching(Integer userId, Booking booking);

  UserDTO setNewUserDetails(User user, NewUserDetailsRequestDTO newUserDetails);

  UserDTO deleteUser(User user);
}
