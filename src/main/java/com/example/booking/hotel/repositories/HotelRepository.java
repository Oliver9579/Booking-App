package com.example.booking.hotel.repositories;

import com.example.booking.hotel.models.Hotel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HotelRepository extends CrudRepository<Hotel, Integer> {

  List<Hotel> findAllByLocation(String location);
}
