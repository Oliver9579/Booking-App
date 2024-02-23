package com.example.booking.hotel.services;

import com.example.booking.exceptions.NoHotelFoundException;
import com.example.booking.exceptions.NotEnoughRoomAvailableException;
import com.example.booking.exceptions.SameDateException;
import com.example.booking.exceptions.TooManyGuestsException;
import com.example.booking.hotel.DTOs.HotelListDTO;
import com.example.booking.hotel.DTOs.HotelRequestDTO;
import com.example.booking.hotel.DTOs.HotelResponseDTO;
import com.example.booking.hotel.models.Hotel;
import com.example.booking.hotel.repositories.HotelRepository;
import com.example.booking.room.models.Room;
import com.example.booking.room.models.RoomType;
import com.example.booking.room.services.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HotelServiceImpl implements HotelService {

  private HotelRepository hotelRepository;
  private RoomService roomService;

  @Override
  public HotelListDTO getAllByLocation(HotelRequestDTO hotelRequest) {

    switch (hotelRequest.getGuests()) {
      case 1:
        return convertHotelsToHotelListDTO(
                getHotelsWithAvailableRoomsByRoomType(hotelRequest, Arrays.asList(RoomType.SINGLE)));
      case 2:
        return convertHotelsToHotelListDTO(
                getHotelsWithAvailableRoomsByRoomType(hotelRequest, Arrays.asList(RoomType.DOUBLE)));
      case 3:
        return convertHotelsToHotelListDTO(
                getHotelsWithAvailableRoomsByRoomType(hotelRequest, Arrays.asList(RoomType.TRIPLE)));
      case 4:
        return convertHotelsToHotelListDTO(
                getHotelsWithAvailableRoomsByRoomType(hotelRequest, Arrays.asList(RoomType.FAMILY)));
      case 5:
        return convertHotelsToHotelListDTO(
                getHotelsWithAvailableRoomsByRoomType(hotelRequest, Arrays.asList(RoomType.DOUBLE, RoomType.TRIPLE)));
      case 6:
        return convertHotelsToHotelListDTO(
                getHotelsWithAvailableRoomsByRoomType(hotelRequest, Arrays.asList(RoomType.FAMILY, RoomType.DOUBLE)));
      case 7:
        return convertHotelsToHotelListDTO(
                getHotelsWithAvailableRoomsByRoomType(hotelRequest, Arrays.asList(RoomType.FAMILY, RoomType.TRIPLE)));
      case 8:
        return convertHotelsToHotelListDTO(
                getHotelsWithAvailableRoomsByRoomType(hotelRequest, Arrays.asList(RoomType.FAMILY, RoomType.FAMILY)));
      default:
        throw new TooManyGuestsException();

    }
  }

  @Override
  public List<Hotel> getHotelsWithAvailableRoomsByRoomType(HotelRequestDTO hotelRequest,
                                                           List<RoomType> roomType) {
    List<Hotel> hotels = hotelRepository.findAllByLocation(hotelRequest.getLocation());
    if (hotels.isEmpty()) throw new NoHotelFoundException();
    if (hotelRequest.getCheckInDate().equals(hotelRequest.getCheckOutDate()))throw new SameDateException();
    for (int i = 0; i < hotels.size(); i++) {
      List<Room> availableRooms = hotels.get(i).getRooms().stream()
              .filter(room -> roomService.isRoomAvailable(room, hotelRequest.getCheckInDate(), hotelRequest.getCheckOutDate()))
              .filter(room -> roomType.contains(room.getRoomType()))
              .collect(Collectors.toList());
      hotels.get(i).setRooms(getRandomRoomsFromEveryAvailableRoom(availableRooms, roomType));
    }
    return hotels;
  }

  @Override
  public List<Room> getRandomRoomsFromEveryAvailableRoom(List<Room> availableRooms, List<RoomType> roomType) {
    if (availableRooms.size() < roomType.size()) throw new NotEnoughRoomAvailableException();
    List<Room> rooms = new ArrayList<>();
    Random random = new Random();
    for (RoomType type : roomType) {
      List<Room> roomsMoreType = availableRooms.stream()
              .filter(room -> room.getRoomType() == type)
              .collect(Collectors.toList());
      if (roomsMoreType.isEmpty()) throw new NotEnoughRoomAvailableException();
      rooms.add(roomsMoreType.get(random.nextInt(roomsMoreType.size())));
    }
    return rooms;
  }

  private HotelListDTO convertHotelsToHotelListDTO(List<Hotel> hotels) {
    return new HotelListDTO(hotels.stream().map(this::convertHotelToResponseDTO).collect(Collectors.toList()));
  }

  private HotelResponseDTO convertHotelToResponseDTO(Hotel hotel) {
    return new HotelResponseDTO(hotel.getId(), hotel.getName(), hotel.getLocation(),
            hotel.getStreet(), roomService.convertToRoomDTO(hotel.getRooms()));
  }

}
