package com.example.booking.email.repositories;

import com.example.booking.email.models.EmailVerificationToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EmailVerificationTokenRepository extends CrudRepository<EmailVerificationToken, Integer> {

  Optional<EmailVerificationToken> findByTokenValue(String tokenValue);

}
