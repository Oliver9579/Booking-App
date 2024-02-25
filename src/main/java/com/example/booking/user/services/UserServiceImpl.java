package com.example.booking.user.services;

import com.example.booking.email.models.EmailVerificationToken;
import com.example.booking.exceptions.UserNotFoundException;
import com.example.booking.registration.models.RegistrationDTO;
import com.example.booking.security.password.PasswordService;
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
  private PasswordService passwordService;

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
  public User convertRegisterDTOToUser(RegistrationDTO rdto) {
    return new User(rdto.getFirstName(), rdto.getLastName(), rdto.getUserName(), rdto.getEmail(),
            passwordService.passwordEncoding(rdto.getPassword()), rdto.getPhoneNumber());
  }

  @Override
  public User verifyPlayer(EmailVerificationToken token) {
    if (token == null || token.getUser() == null) return null;
    User player = token.getUser();
    player.setEnabled(true);
    return userRepository.save(player);
  }

  @Override
  public List<UserDTO> getUsers() {
    return userRepository.findAll().stream().map(this::convertUserToDTO).collect(Collectors.toList());
  }
}
