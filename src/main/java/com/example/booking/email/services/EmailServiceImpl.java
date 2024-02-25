package com.example.booking.email.services;

import com.example.booking.user.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService {

  @Value("${spring.mail.username}")
  private String sender;
  private String port = "8080";
  private String host;
  @Autowired
  private JavaMailSender mailSender;
  @Autowired
  private SpringTemplateEngine templateEngine;

  public EmailServiceImpl() {
    host = InetAddress.getLoopbackAddress().getHostAddress();
  }

  public EmailServiceImpl(JavaMailSender mailSender, SpringTemplateEngine templateEngine) {
    this();
    this.mailSender = mailSender;
    this.templateEngine = templateEngine;
  }

  @Override
  public Message sendMail(MimeMessage message) {
    if (message == null) return null;
    mailSender.send(message);
    return message;
  }

  @Override
  public MimeMessage createVerificationMail(User user) throws MessagingException {
    if (user == null) return null;
    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = generateHelper(message);

    helper.setFrom(sender);
    helper.setTo(user.getEmail());
    helper.setSubject("Complete your registration!");

    Multipart multipartContent = new MimeMultipart("alternative");
    multipartContent.addBodyPart(createVerificationPlainTextPart(user));
    multipartContent.addBodyPart(createVerificationHtmlPart(generateVerificationPropertiesMap(user)));
    message.setContent(multipartContent);
    mailSender.send(message);
    return message;
  }

  @Override
  public MimeBodyPart createVerificationPlainTextPart(User user) throws MessagingException {
    if (user == null) return null;
    String tokenValue = user.getVerificationToken().getTokenValue();
    String plainText = String.format("Hi %s!\n\n"
            + "To activate your account, please click the following link: "
            + "http://%s:%s/verify?token=%s", user.getUsername(), host, port, tokenValue);
    MimeBodyPart plainTextPart = new MimeBodyPart();
    plainTextPart.setContent(plainText, "text/plain");
    return plainTextPart;
  }

  @Override
  public MimeBodyPart createVerificationHtmlPart(Map<String, Object> properties) throws MessagingException {
    if (properties == null || properties.isEmpty()) return null;
    Context context = new Context();
    context.setVariables(properties);
    String htmlText = templateEngine.process("verification.html", context);
    MimeBodyPart htmlPart = new MimeBodyPart();
    htmlPart.setContent(htmlText, "text/html");
    return htmlPart;
  }

  @Override
  public MimeMessageHelper generateHelper(MimeMessage message) throws MessagingException {
    if (message == null) return null;
    return new MimeMessageHelper(
            message,
            MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
            StandardCharsets.UTF_8.name());
  }

  @Override
  public Map<String, Object> generateVerificationPropertiesMap(User user) {
    if (user == null) return null;
    Map<String, Object> properties = new HashMap<>();
    properties.put("name", user.getUsername());
    properties.put("token", user.getVerificationToken().getTokenValue());
    properties.put("host", host);
    properties.put("port", port);
    return properties;
  }

  @Override
  public void setSender(String sender) {
    this.sender = sender;
  }

  @Override
  public void setHost(String host) {
    this.host = host;
  }

  @Override
  public void setPort(String port) {
    this.port = port;
  }

}