package com.example.booking.room.services;

import com.example.booking.booking.DTOs.bookingHotel.BookedRoom;
import com.example.booking.booking.DTOs.bookingHotel.BookingRoomDTO;
import com.example.booking.booking.models.Booking;
import com.example.booking.date.models.Days;
import com.example.booking.date.services.DaysService;
import com.example.booking.exceptions.IdNotFoundException;
import com.example.booking.hotel.models.Hotel;
import com.example.booking.room.DTOs.RoomDTO;
import com.example.booking.room.models.Room;
import com.example.booking.room.models.RoomType;
import com.example.booking.room.repositories.RoomRepository;
import com.example.booking.user.models.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {

  private RoomRepository roomRepository;
  private DaysService daysService;

  @Override
  public Boolean isRoomAvailable(Room room, String checkInDate, String checkOutDate) {
    boolean isAvailable = true;
    List<String> travelDates = daysService.getFullTravelDates(checkInDate, checkOutDate);
    for (String travelDate : travelDates) {
      if (room.getUnavailable().stream().anyMatch(days -> days.getDate().equals(travelDate))) {
        isAvailable = false;
      }
    }
    return isAvailable;
  }

  @Override
  public List<RoomDTO> convertToRoomDTO(List<Room> rooms, List<Long> availableNumbers,
                                        String checkInDate, String checkOutDate) {
    List<RoomDTO> result = new ArrayList<>();
    for (int i = 0; i < rooms.size(); i++) {
      result.add(new RoomDTO(rooms.get(i).getId(), rooms.get(i).getRoomType(),
              rooms.get(i).getCapacity(),
              rooms.get(i).getPricePerNight() * (daysService.getFullTravelDates(checkInDate, checkOutDate).size() - 1),
              availableNumbers.get(i)));
    }
    return result;
  }

  @Override
  public List<Room> getRoomsByType(Hotel hotel, RoomType[] roomTypes) {
    List<Room> responsesRooms = new ArrayList<>();
    for (RoomType roomType : roomTypes) {
      if (hotel.getRooms().stream().anyMatch(room -> room.getRoomType() == roomType)) {
        responsesRooms.add(hotel.getRooms().stream().filter(room -> room.getRoomType() == roomType).findFirst().get());
      }
    }
    return responsesRooms;
  }

  @Override
  public List<Long> getRoomsCountByType(List<Room> rooms) {
    List<Long> counts = new ArrayList<>();
    for (RoomType roomType : RoomType.values()) {
      if (rooms.stream().anyMatch(room -> room.getRoomType() == roomType)) {
        counts.add(rooms.stream().filter(room -> room.getRoomType() == roomType).count());
      }
    }
    return counts;
  }

  @Override
  public List<Room> setUnavailableDates(List<Room> rooms, List<Days> days) {
    for (Room room : rooms) {
      List<Days> unavailableDates = room.getUnavailable();
      unavailableDates.addAll(days);
      room.setUnavailable(unavailableDates);
      roomRepository.save(room);
    }
    return rooms;
  }

  @Override
  public List<Room> getOneRoomForEachGivenType(List<Room> availableRooms, List<BookingRoomDTO> roomTypesWithNumber) {
    List<Room> rooms = new ArrayList<>();
    for (int i = 0; i < roomTypesWithNumber.size(); i++) {
      for (int j = 0; j < roomTypesWithNumber.get(i).getRoomCount(); j++) {
        int index = i;
        Room room = availableRooms.stream().filter(
                room1 -> room1.getRoomType() == roomTypesWithNumber.get(index).getRoomType()).findFirst().get();
        rooms.add(room);
        availableRooms.remove(room);
      }
    }
    return rooms;
  }

  @Override
  public Room save(Room room) {
    return roomRepository.save(room);
  }

  @Override
  public List<BookedRoom> getBookedRoomsWithUnavailableDays(User user) {
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

  @Override
  public void updateRoomAvailability(List<BookedRoom> bookedRooms) {
    for (BookedRoom bookedRoom : bookedRooms) {
      Room room = bookedRoom.getRoom();
      Set<Days> daysSet = bookedRoom.getDays();
      for (Days day : daysSet) {
        room.getUnavailable().remove(day);
        save(room);
      }
    }
  }
}
