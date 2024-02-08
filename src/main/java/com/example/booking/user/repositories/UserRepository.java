package com.example.booking.user.repositories;

import com.example.booking.user.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

  Optional<User> findByUserName(String userName);

  Optional<User> findByEmail(String email);

  Optional<User> findByPhoneNumber(String phoneNumber);

  Optional<User> findById(int id);

  List<User> findAll();
}
