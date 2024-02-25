package com.example.booking.email.services;

import com.example.booking.email.models.EmailVerificationToken;
import com.example.booking.email.repositories.EmailVerificationTokenRepository;
import com.example.booking.exceptions.VerificationTokenNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailVerificationTokenServiceImpl implements EmailVerificationTokenService {

  private EmailVerificationTokenRepository tokenRepository;

  @Override
  public EmailVerificationToken getByTokenValue(String tokenValue) throws VerificationTokenNotFoundException {
    return tokenRepository.findByTokenValue(tokenValue).orElseThrow(VerificationTokenNotFoundException::new);
  }

}
