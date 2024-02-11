package com.example.booking.hotel.repositories;

import com.example.booking.hotel.models.Hotel;
import org.springframework.data.repository.CrudRepository;

public interface HotelRepository extends CrudRepository<Hotel, Integer> {

}
