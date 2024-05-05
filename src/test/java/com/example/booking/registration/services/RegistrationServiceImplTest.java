package com.example.booking.registration.services;

import com.example.booking.email.services.EmailService;
import com.example.booking.exceptions.AlreadyTakenException;
import com.example.booking.registration.models.RegistrationDTO;
import com.example.booking.security.password.PasswordService;
import com.example.booking.user.models.User;
import com.example.booking.user.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationServiceImplTest {

  @Mock
  private UserService mockUserService;
  @Mock
  private PasswordService mockPasswordService;
  @Mock
  private EmailService mockEmailService;
  private Session session;
  private RegistrationServiceImpl registrationService;
  private RegistrationDTO rdto = new RegistrationDTO("firstName", "lastName", "username",
          "email", "password", "1234567");
  private User user = new User("username", "password", "email", "phoneNumber");

  @Before
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    registrationService = Mockito.spy(new RegistrationServiceImpl(mockUserService, mockPasswordService, mockEmailService));
  }

  @Test(expected = AlreadyTakenException.class)
  public void testRegisterWhenUserInfosAlreadyTaken() throws MessagingException {
    RegistrationDTO rdto2 = new RegistrationDTO();
    doThrow(AlreadyTakenException.class).when(registrationService).validateRegistration(rdto2);
    MimeMessage message = new MimeMessage(session);

    registrationService.register(rdto);

    verify(registrationService, times(1)).validateRegistration(rdto);
    verify(mockUserService, times(0)).save(any(User.class));
    verify(mockEmailService, times(0)).sendMail(message);
  }

  @Test
  public void testRegister() throws MessagingException {
    doNothing().when(registrationService).validateRegistration(rdto);
    when(mockUserService.convertRegisterDTOToUser(rdto)).thenReturn(user);
    MimeMessage message = new MimeMessage(session);
    when(mockEmailService.createVerificationMail(any(User.class))).thenReturn(message);
    when(mockEmailService.sendMail(message)).thenReturn(message);
    when(mockUserService.save(any(User.class))).thenReturn(user);

    User newUser = registrationService.register(rdto);

    assertEquals(newUser.getUsername(), user.getUsername());
    assertEquals(newUser.getEmail(), user.getEmail());
    assertEquals(newUser.getPassword(), user.getPassword());
    assertEquals(newUser.getPhoneNumber(), user.getPhoneNumber());

    verify(registrationService, times(1)).validateRegistration(rdto);
    verify(mockUserService, times(1)).save(any(User.class));
    verify(mockEmailService, times(1)).sendMail(message);
  }

}
