package com.example.booking.user.models;

import com.example.booking.booking.models.Booking;
import com.example.booking.email.models.EmailVerificationToken;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @NotNull
  @JsonIgnore
  private int id;

  @NotNull
  @Column(name = "first_name")
  @JsonIgnore
  private String firstName;

  @NotNull
  @Column(name = "last_name")
  @JsonIgnore
  private String lastName;

  @NotNull
  @Column(name = "username", unique = true)
  @JsonIgnore
  private String userName;

  @NotNull
  @Column(name = "email", unique = true)
  @JsonIgnore
  private String email;

  @NotNull
  @Column(name = "password")
  @JsonIgnore
  private String password;

  @NotNull
  @Column(name = "phone_number", unique = true)
  @JsonIgnore
  private String phoneNumber;

  private boolean enabled;

  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
  private EmailVerificationToken verificationToken;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Booking> booking = new ArrayList<>();

  public User(String firstName, String lastName, String userName, String email, String password, String phoneNumber) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.userName = userName;
    this.email = email;
    this.password = password;
    this.phoneNumber = phoneNumber;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getUsername() {
    return userName;
  }

  @Override
  public boolean isAccountNonExpired() {
    return false;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }
}
