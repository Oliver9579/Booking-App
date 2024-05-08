package com.example.booking.security;

import com.example.booking.security.password.PasswordServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockedStatic;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCrypt;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;


@RunWith(MockitoJUnitRunner.class)
public class PasswordServiceImplTest {

  private PasswordServiceImpl passwordService;

  @Before
  public void setUp() throws Exception {
    passwordService = new PasswordServiceImpl();
  }

  @Test
  public void passwordEncoding_changesThePassword_when_passwordIsGiven() {
    String password = "password";
    try (MockedStatic<BCrypt> mockedBCrypt = mockStatic(BCrypt.class)) {

      String result = passwordService.passwordEncoding(password);

      assertNotEquals(password, result);
    }
  }

  @Test
  public void isPasswordMatch_returnsTrue_when_BCyptCheckpwReturnsTrue() {
    String password = "password";
    String encodedPassword = "encodedPassword";
    try (MockedStatic<BCrypt> mockedBCrypt = mockStatic(BCrypt.class)) {
      mockedBCrypt.when(() -> BCrypt.checkpw(anyString(), anyString())).thenReturn(true);

      boolean result = passwordService.isPasswordMatch(password, encodedPassword);

      assertTrue(result);
    }
  }

  @Test
  public void isPasswordMatch_returnsFalse_when_BCyptCheckpwReturnsFalse() {
    String password = "password";
    String encodedPassword = "encodedPassword";
    try (MockedStatic<BCrypt> mockedBCrypt = mockStatic(BCrypt.class)) {
      mockedBCrypt.when(() -> BCrypt.checkpw(anyString(), anyString())).thenReturn(false);

      boolean result = passwordService.isPasswordMatch(password, encodedPassword);

      assertFalse(result);
    }
  }

}
