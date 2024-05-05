package com.example.booking.login.services;

import com.example.booking.exceptions.UnverifiedAccountException;
import com.example.booking.exceptions.WrongPasswordException;
import com.example.booking.login.models.LoginDTO;
import com.example.booking.login.models.TokenDTO;
import com.example.booking.security.config.JwtTokenUtil;
import com.example.booking.security.password.PasswordService;
import com.example.booking.user.models.User;
import com.example.booking.user.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class LoginServiceImplTest {

  private LoginService loginService;
  @Mock
  private UserService mockUserService;
  @Mock
  private PasswordService mockPasswordService;
  @Mock
  private JwtTokenUtil mockJwtTokenUtil;

  private LoginDTO loginDTO;

  @Before
  public void setup() {
    MockitoAnnotations.openMocks(this);
    this.loginService = new LoginServiceImpl(mockUserService, mockPasswordService, mockJwtTokenUtil);
    loginDTO = new LoginDTO("Oli", "Oli12345");
    ReflectionTestUtils.setField(loginService, "expirationTime", 3_600_000L);
  }

  @Test(expected = NoSuchElementException.class)
  public void createTokenDtoResponse_should_throwNoSuchElementException_when_UserIsNull() {
    loginDTO = new LoginDTO("invalidUsername", "123");
    when(mockUserService.getByUsername(loginDTO.getUserName())).thenThrow(new NoSuchElementException());

    loginService.createTokenDtoResponse(loginDTO);
  }

  @Test(expected = WrongPasswordException.class)
  public void createTokenDtoResponse_should_throwWrongPasswordException_when_passwordIsIncorrect() {
    User user = new User();
    when(mockUserService.getByUsername(any())).thenReturn(user);
    when(mockPasswordService.isPasswordMatch(any(), any())).thenReturn(false);

    loginService.createTokenDtoResponse(loginDTO);
  }

  @Test(expected = UnverifiedAccountException.class)
  public void createTokenDtoResponse_should_throwUnverifiedAccountException_when_userIsUnverified() {
    User user = new User();
    when(mockUserService.getByUsername(any())).thenReturn(user);
    when(mockPasswordService.isPasswordMatch(any(), any())).thenReturn(true);

    loginService.createTokenDtoResponse(loginDTO);
  }

  @Test
  public void createTokenDtoResponse_should_returnCorrectResponseEntity_when_ExistingUserIsGiven() {
    User user = new User(1, "Olivér", "Szabó-Temple", "Oli",
            "szabo.oliver2001@gmail.com", "Oli12345", "1234567", "2001-03-29",
            "hungarian", "male", null, true, null, null, null);
    when(mockUserService.getByUsername(loginDTO.getUserName())).thenReturn(user);

    when(mockPasswordService.isPasswordMatch(loginDTO.getPassword(), user.getPassword())).thenReturn(true);
    when(mockJwtTokenUtil.createJwtsToken(any())).thenReturn("token");
    TokenDTO response = loginService.createTokenDtoResponse(loginDTO);

    assertEquals("ok", response.getStatus());
    assertTrue(response.getToken() != null && !response.getToken().isEmpty());
  }

}
