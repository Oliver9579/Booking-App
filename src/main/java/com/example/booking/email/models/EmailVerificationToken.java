package com.example.booking.email.models;

import com.example.booking.user.models.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "verification_tokens")
public class EmailVerificationToken {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @NotNull
  private String tokenValue;
  @NotNull
  private Long createdAt;
  @OneToOne
  private User user;

  public EmailVerificationToken() {
    this.tokenValue = UUID.randomUUID().toString();
    this.createdAt = Calendar.getInstance().getTimeInMillis() / 1000;
  }

  public EmailVerificationToken(User user) {
    this();
    this.user = user;
  }

}
