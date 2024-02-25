package com.example.booking.email.services;

import com.example.booking.email.models.EmailVerificationToken;

public interface EmailVerificationTokenService {

  EmailVerificationToken getByTokenValue(String tokenValue);

}
