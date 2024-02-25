package com.example.booking.email.services;

import com.example.booking.user.models.User;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import java.util.Map;

public interface EmailService {

  Message sendMail(MimeMessage message);

  MimeMessage createVerificationMail(User user) throws MessagingException;

  MimeBodyPart createVerificationPlainTextPart(User user) throws MessagingException;

  MimeBodyPart createVerificationHtmlPart(Map<String, Object> properties) throws MessagingException;

  MimeMessageHelper generateHelper(MimeMessage message) throws MessagingException;

  Map<String, Object> generateVerificationPropertiesMap(User user);

  void setSender(String sender);

  void setHost(String host);

  void setPort(String port);

}
