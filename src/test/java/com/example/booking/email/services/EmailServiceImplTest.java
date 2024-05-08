package com.example.booking.email.services;

import com.example.booking.email.models.EmailVerificationToken;
import com.example.booking.user.models.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class EmailServiceImplTest {

  private String sender = "booking.2024.test@gmail.com";
  private String port = "3000";
  private String host = "localhost";
  private MimeMessage message;
  private String expectedText;
  private Session session;
  private MimeMessageHelper helper;
  private int helperMode = MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED;
  private String charSet = StandardCharsets.UTF_8.name();
  private Map<String, Object> verificationProperties;
  private EmailService emailService;
  private User user;
  private EmailVerificationToken verificationToken;
  @Mock
  private JavaMailSender mailSender;
  @Mock
  private SpringTemplateEngine templateEngine;

  @Before
  public void setup() throws MessagingException {
    MockitoAnnotations.openMocks(this);
    message = new MimeMessage(session);
    emailService = new EmailServiceImpl(mailSender, templateEngine);
    emailService.setSender(sender);
    emailService.setHost(host);
    emailService.setPort(port);
    verificationProperties = new HashMap<>();
    verificationToken = new EmailVerificationToken(user);
    verificationToken.setTokenValue("123");
    user = new User(1, "Olivér", "Szabó-Temple", "Oli",
            "szabo.oliver2001@gmail.com", "Oli12345", "1234567", "2001-03-29",
            "hungarian", "male", null, true, verificationToken, null, null);
    expectedText = String.format("Hi %s!\n\n"
                    + "To activate your account, please click the following link: http://%s:%s/verify?token=%s",
            user.getUsername(), host, port, verificationToken.getTokenValue());
  }

  @Test
  public void testSendMailWithNullIsGiven() {
    assertNull(emailService.sendMail(null));
  }

  @Test
  public void testSendMail() throws MessagingException, IOException {
    message.setText("Hello");
    String expected = "Hello";
    doNothing().when(mailSender).send(message);

    emailService.sendMail(message);

    assertEquals(expected, message.getContent());
    verify(mailSender, times(1)).send(message);
  }

  @Test
  public void testCreateVerificationMailWithNullIsGiven() throws MessagingException {
    assertNull(emailService.createVerificationMail(null));
  }

  @Test
  public void testCreateVerificationMail() throws MessagingException, IOException {
    emailService = Mockito.spy(emailService);
    helper = new MimeMessageHelper(message, helperMode, charSet);
    when(mailSender.createMimeMessage()).thenReturn(message);
    doReturn(helper).when(emailService).generateHelper(message);
    verificationProperties.put("name", user.getUsername());
    verificationProperties.put("token", verificationToken.getTokenValue());
    when(emailService.generateVerificationPropertiesMap(user)).thenReturn(verificationProperties);
    helper.setFrom(sender);
    helper.setTo(user.getEmail());
    helper.setSubject("Complete your registration!");

    MimeMessage result = emailService.createVerificationMail(user);
    int messageParts = ((Multipart) result.getDataHandler().getContent()).getCount();

    assertEquals(message, result);
    assertEquals(2, messageParts);
    verify(mailSender, times(1)).send(message);
  }

  @Test
  public void testCreateVerificationPlainTextPartWithNullIsGiven() throws MessagingException {
    assertNull(emailService.createVerificationPlainTextPart(null));
  }

  @Test
  public void testCreateVerificationPlainTextPart() throws MessagingException, IOException {
    MimeBodyPart bodyPart = emailService.createVerificationPlainTextPart(user);
    assertEquals(expectedText, bodyPart.getContent().toString());
  }

  @Test
  public void testCreateVerificationHtmlPartWithNullIsGiven() throws MessagingException {
    assertNull(emailService.createVerificationHtmlPart(null));
  }

  @Test
  public void testCreateVerificationHtmlPartWithEmptyMapIsGiven() throws MessagingException {
    assertNull(emailService.createVerificationHtmlPart(new HashMap<>()));
  }

  @Test
  public void testCreateVerificationHtmlPart() throws MessagingException, IOException {
    verificationProperties.put("name", user.getUsername());
    verificationProperties.put("token", verificationToken.getTokenValue());
    verificationProperties.put("port", port);
    verificationProperties.put("host", host);
    Context context = new Context();
    context.setVariables(verificationProperties);
    when(templateEngine.process(anyString(), any(Context.class))).thenReturn(expectedText);

    MimeBodyPart bodyPart = emailService.createVerificationHtmlPart(verificationProperties);

    assertEquals(expectedText, bodyPart.getContent().toString());
  }

  @Test
  public void testGenerateHelperWithNullIsGiven() throws MessagingException {
    assertNull(emailService.generateHelper(null));
  }

  @Test
  public void testGenerateHelper() throws MessagingException {
    MimeMessageHelper expected = new MimeMessageHelper(message, helperMode, charSet);
    MimeMessageHelper actual = emailService.generateHelper(message);
    assertEquals(expected.getMimeMessage(), actual.getMimeMessage());
  }

  @Test
  public void testGenerateVerificationPropertiesMapWithNullIsGiven() {
    assertNull(emailService.generateVerificationPropertiesMap(null));
  }

  @Test
  public void testGenerateVerificationPropertiesMap() {
    Map<String, Object> expected = new HashMap<>();
    expected.put("name", user.getUsername());
    expected.put("token", verificationToken.getTokenValue());
    expected.put("host", host);
    expected.put("port", port);
    verificationProperties.put("name", user.getUsername());
    verificationProperties.put("token", verificationToken.getTokenValue());

    Map<String, Object> actual = emailService.generateVerificationPropertiesMap(user);

    assertEquals(expected, actual);
  }


}
