package com.example.booking.user.services;

import com.example.booking.exceptions.UserNotFoundException;
import com.example.booking.user.models.User;
import com.example.booking.user.models.UserDTO;
import com.example.booking.user.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

  private UserRepository userRepository;

  @Override
  public User getByEmail(String email) throws UserNotFoundException {
    return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
  }

  @Override
  public User getByPhoneNumber(String phoneNumber) throws UserNotFoundException {
    return userRepository.findByPhoneNumber(phoneNumber).orElseThrow(UserNotFoundException::new);
  }

  @Override
  public User getByUsername(String userName) throws UserNotFoundException {
    return userRepository.findByUserName(userName).orElseThrow(UserNotFoundException::new);
  }

  @Override
  public User getById(int id) {
    return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
  }

  @Override
  public User save(User user) {
    return userRepository.save(user);
  }

  @Override
  public UserDTO convertUserToDTO(User user) {
    return new UserDTO(user.getId(), user.getFirstName() + " " + user.getLastName(),
            user.getUsername(), user.getEmail(), user.getPhoneNumber());
  }

  @Override
  public List<UserDTO> getUsers() {
    return userRepository.findAll().stream().map(this::convertUserToDTO).collect(Collectors.toList());
  }
}
