package com.example.booking.user.services;

import com.example.booking.booking.models.Booking;
import com.example.booking.email.models.EmailVerificationToken;
import com.example.booking.exceptions.AlreadyTakenException;
import com.example.booking.exceptions.ForbiddenActionException;
import com.example.booking.exceptions.UserNotFoundException;
import com.example.booking.registration.models.RegistrationDTO;
import com.example.booking.security.password.PasswordService;
import com.example.booking.user.models.NewUserDetailsRequestDTO;
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
    return new UserDTO(user.getId(), user.getFirstName() + " " + user.getLastName(), user.getUsername(),
            user.getEmail(), user.getPhoneNumber(), user.getDateOfBirth(), user.getNationality(), user.getGender(),
            user.getAddress());
  }

  @Override
  public User convertRegisterDTOToUser(RegistrationDTO rdto) {
    return new User(rdto.getFirstName(), rdto.getLastName(), rdto.getUserName(), rdto.getEmail(),
            passwordService.passwordEncoding(rdto.getPassword()), rdto.getPhoneNumber());
  }

  @Override
  public User verifyUser(EmailVerificationToken token) {
    if (token == null || token.getUser() == null) return null;
    User user = token.getUser();
    user.setEnabled(true);
    return userRepository.save(user);
  }

  @Override
  public Boolean isUserIdMatching(Integer userId, Booking booking) {
    if (userId == null || !userId.equals(booking.getUser().getId())) throw new ForbiddenActionException();
    return true;
  }

  @Override
  public List<UserDTO> getUsers() {
    return userRepository.findAll().stream().map(this::convertUserToDTO).collect(Collectors.toList());
  }

  @Override
  public UserDTO setNewUserDetails(User user, NewUserDetailsRequestDTO newUserDetails) {
    emailAndPhoneNumberAlreadyExist(newUserDetails);

    user.setFirstName(newUserDetails.getFirstName());
    user.setLastName(newUserDetails.getLastName());
    user.setEmail(newUserDetails.getEmail());
    user.setPhoneNumber(newUserDetails.getPhoneNumber());
    user.setDateOfBirth(newUserDetails.getDateOfBirth());
    user.setNationality(newUserDetails.getNationality());
    user.setGender(newUserDetails.getGender());
    user.setAddress(newUserDetails.getAddress());

    userRepository.save(user);
    return convertUserToDTO(user);
  }

  private boolean emailAndPhoneNumberAlreadyExist(NewUserDetailsRequestDTO newUserDetails) {
    if (getByEmail(newUserDetails.getEmail()) != null) {
      throw new AlreadyTakenException("This email is already exists!");
    }else if (getByPhoneNumber(newUserDetails.getPhoneNumber()) != null) {
      throw new AlreadyTakenException("This phone number is already exists!");
    }
    return false;
  }

}