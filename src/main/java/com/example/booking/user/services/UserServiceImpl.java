package com.example.booking.user.services;

import com.example.booking.booking.DTOs.bookingCar.BookedCar;
import com.example.booking.booking.DTOs.bookingHotel.BookedRoom;
import com.example.booking.booking.models.Booking;
import com.example.booking.car.models.Car;
import com.example.booking.car.services.CarService;
import com.example.booking.date.models.Days;
import com.example.booking.date.services.DaysService;
import com.example.booking.email.models.EmailVerificationToken;
import com.example.booking.exceptions.AlreadyTakenException;
import com.example.booking.exceptions.ForbiddenActionException;
import com.example.booking.exceptions.UserNotFoundException;
import com.example.booking.registration.models.RegistrationDTO;
import com.example.booking.room.models.Room;
import com.example.booking.room.services.RoomService;
import com.example.booking.seat.models.Seat;
import com.example.booking.seat.services.SeatService;
import com.example.booking.security.password.PasswordService;
import com.example.booking.user.models.NewUserDetailsRequestDTO;
import com.example.booking.user.models.User;
import com.example.booking.user.models.UserDTO;
import com.example.booking.user.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

  private UserRepository userRepository;
  private PasswordService passwordService;
  private DaysService daysService;
  private CarService carService;
  private RoomService roomService;
  private SeatService seatService;

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
    return new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getUsername(),
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
    emailAndPhoneNumberAlreadyExist(newUserDetails, user);

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

  @Override
  public UserDTO deleteUser(User user) {

    List<BookedCar> bookedCars = getBookedCarsWithUnavailableDays(user);
    updateCarAvailability(bookedCars);
    List<BookedRoom> bookedRooms = getBookedRoomsWithUnavailableDays(user);
    updateRoomAvailability(bookedRooms);
    updateSeatsAvailability(user);
    userRepository.delete(user);
    return convertUserToDTO(user);
  }

  private List<BookedCar> getBookedCarsWithUnavailableDays(User user) {
    List<BookedCar> bookedCars = new ArrayList<>();
    for (Booking booking : user.getBooking()) {
      if (booking.getCar() != null) {
        Set<Days> days = daysService.getFullTravelDates(booking.getStartDate(), booking.getEndDate()).stream()
                .map(date -> daysService.getByDate(date)).collect(Collectors.toSet());
        bookedCars.add(new BookedCar(booking.getCar(), days));
      }
    }
    return bookedCars;
  }

  private void updateCarAvailability(List<BookedCar> bookedCars) {
    for (BookedCar bookedCar : bookedCars) {
      Car car = bookedCar.getCar();
      Set<Days> daysSet = bookedCar.getDays();
      for (Days day : daysSet) {
        car.getUnavailable().remove(day);
        carService.save(car);
      }
    }
  }

  private List<BookedRoom> getBookedRoomsWithUnavailableDays(User user) {
    List<BookedRoom> bookedRooms = new ArrayList<>();
    for (Booking booking : user.getBooking()) {
      for (Room room : booking.getBookedRooms()) {
        if (booking.getHotel() != null) {
          Set<Days> days = daysService.getFullTravelDates(booking.getStartDate(), booking.getEndDate()).stream()
                  .map(date -> daysService.getByDate(date)).collect(Collectors.toSet());
          bookedRooms.add(new BookedRoom(room, days));
        }
      }
    }
    return bookedRooms;
  }

  private void updateRoomAvailability(List<BookedRoom> bookedRooms) {
    for (BookedRoom bookedRoom : bookedRooms) {
      Room room = bookedRoom.getRoom();
      Set<Days> daysSet = bookedRoom.getDays();
      for (Days day : daysSet) {
        room.getUnavailable().remove(day);
        roomService.save(room);
      }
    }
  }

  private void updateSeatsAvailability(User user) {
    for (Booking booking : user.getBooking()) {
      if (booking.getOutboundFlight() != null) {
        List<Seat> seats = booking.getBookedSeats();
        for (Seat seat : seats) {
          seat.setAvailability(true);
          seatService.save(seat);
        }
      }
    }
  }

  private boolean emailAndPhoneNumberAlreadyExist(NewUserDetailsRequestDTO newUserDetails, User user) {
    try {
      if (getByEmail(newUserDetails.getEmail()) != null
              && !(getByEmail(newUserDetails.getEmail()).equals(user))) {
        throw new AlreadyTakenException("This email is already exists!");
      } else if (getByPhoneNumber(newUserDetails.getPhoneNumber()) != null
              && !(getByPhoneNumber(newUserDetails.getPhoneNumber()).equals(user))) {
        throw new AlreadyTakenException("This phone number is already exists!");
      }
    } catch (UserNotFoundException e) {
    }
    return false;
  }
}