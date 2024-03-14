package com.example.booking.hotel.services;

import com.example.booking.date.services.DaysService;
import com.example.booking.exceptions.IdNotFoundException;
import com.example.booking.exceptions.NoHotelFoundException;
import com.example.booking.exceptions.SameDateException;
import com.example.booking.hotel.DTOs.*;
import com.example.booking.hotel.models.Hotel;
import com.example.booking.hotel.repositories.HotelRepository;
import com.example.booking.room.models.Room;
import com.example.booking.room.models.RoomBookingDTO;
import com.example.booking.room.models.RoomType;
import com.example.booking.room.services.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HotelServiceImpl implements HotelService {

  private HotelRepository hotelRepository;
  private RoomService roomService;
  private DaysService daysService;

  @Override
  public HotelListDTO getAllByLocation(HotelRequestDTO hotelRequest) {
    long cnt = 0;
    List<Hotel> hotels = hotelRepository.findAllByLocation(hotelRequest.getLocation());
    if (hotels.isEmpty()) throw new NoHotelFoundException();
    if (hotelRequest.getCheckInDate().equals(hotelRequest.getCheckOutDate())) throw new SameDateException();
    for (int i = 0; i < hotels.size(); i++) {
      List<Room> availableRooms = hotels.get(i).getRooms().stream()
              .filter(room -> roomService.isRoomAvailable(room, hotelRequest.getCheckInDate(), hotelRequest.getCheckOutDate()))
              .collect(Collectors.toList());
      if (availableRooms.isEmpty()) {
        hotels.remove(i);
        i--;
      } else {
        hotels.get(i).setRooms(availableRooms);
      }
    }
    return convertHotelsToHotelListDTO(hotels, hotelRequest.getCheckInDate(), hotelRequest.getCheckOutDate());
  }

  @Override
  public List<AllHotelDTO> getAllHotel() {
    List<Hotel> hotels = hotelRepository.findAll();
    return (hotels.stream().map(hotel -> new AllHotelDTO(
            hotel.getId(), hotel.getName(), hotel.getLocation(), hotel.getStreet(), hotel.getStars(),
            hotel.getImg()))).collect(Collectors.toList());
  }

  @Override
  public Hotel getHotelById(Integer id) {
    return hotelRepository.findById(id).orElseThrow(IdNotFoundException::new);
  }

  @Override
  public HotelBookingResponseDTO convertToResponseDTO(Hotel hotel) {
    return new HotelBookingResponseDTO(hotel.getId(), hotel.getName(), hotel.getLocation(), hotel.getStreet(),
            hotel.getStars(), hotel.getRooms().stream().map(room -> new RoomBookingDTO(room.getId(), room.getRoomType(),
            room.getCapacity(), room.getPricePerNight())).collect(Collectors.toList()));
  }


  private HotelListDTO convertHotelsToHotelListDTO(List<Hotel> hotels, String checkInDate, String checkOutDate) {
    return new HotelListDTO(hotels.stream()
            .map(hotel -> convertHotelToResponseDTO(hotel, checkInDate, checkOutDate)).
            collect(Collectors.toList()));
  }

  private HotelResponseDTO convertHotelToResponseDTO(Hotel hotel, String checkInDate, String checkOutDate) {
    return new HotelResponseDTO(hotel.getId(), hotel.getName(), hotel.getLocation(),
            hotel.getStreet(), hotel.getStars(), (daysService.getFullTravelDates(checkInDate, checkOutDate).size()) - 1,
            hotel.getImg(), roomService.convertToRoomDTO(roomService.getRoomsByType(hotel, RoomType.values()),
            roomService.getRoomsCountByType(hotel.getRooms()),
            checkInDate, checkOutDate));
  }

}
