package com.example.booking.user.controllers;

import com.example.booking.car.models.Car;
import com.example.booking.review.DTOs.ReviewRequestDTO;
import com.example.booking.user.models.NewUserDetailsRequestDTO;
import com.example.booking.user.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@Sql("/data.sql")
public class UserControllerIT {

  @Autowired
  private MockMvc mockMvc;
  private final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
          MediaType.APPLICATION_JSON.getSubtype(),
          StandardCharsets.UTF_8);
  private ObjectMapper mapper;
  private User user1;
  private User user2;
  private User user3;
  private User user4;
  private Authentication userAuth1;
  private Authentication userAuth2;
  private Authentication userAuth3;
  private Authentication userAuth4;


  @Before
  public void setup() {
    mapper = new ObjectMapper();
    user1 = new User(100, "", "", "Oli", "booking.2024.test@gmail.com",
            "Oli12345", "123456789");
    user2 = new User(101, "", "", "Oli2", "szabotemple.oliver2001@gmail.com",
            "Oli12345", "12345678910");
    user3 = new User(105, "", "", "deletableUser", "deletableUser@gmail.com",
            "Oli12345", "12345678913");
    user4 = new User(0, "", "", "", "",
            "", "");
    userAuth1 = new UsernamePasswordAuthenticationToken(user1, null, null);
    userAuth2 = new UsernamePasswordAuthenticationToken(user2, null, null);
    userAuth3 = new UsernamePasswordAuthenticationToken(user3, null, null);
    userAuth4 = new UsernamePasswordAuthenticationToken(user4, null, null);
  }

  @Test
  public void getUser_should_ReturnError_when_UserIdNotFound() throws Exception {
    mockMvc.perform(get("/api/users")
                    .principal(userAuth4))
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnauthorized())
            .andExpect(jsonPath("$.status", Matchers.is("error")))
            .andExpect(jsonPath("$.message", Matchers.is("No such user.")));
  }

  @Test
  public void getUser_should_ReturnTheUser() throws Exception {
    mockMvc.perform(get("/api/users")
                    .principal(userAuth1))
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(100))
            .andExpect(jsonPath("$.username").value("Oli"))
            .andExpect(jsonPath("$.email").value("booking.2024.test@gmail.com"))
            .andExpect(jsonPath("$.phoneNumber").value("123456789"));
  }

  @Test
  public void editUserDetails_should_ReturnError_when_UserIdNotFound() throws Exception {
    NewUserDetailsRequestDTO newUser = new NewUserDetailsRequestDTO("Mark", "Szabo",
            "szabotemple.oliver2001@gmail.com", "12345678910",
            "2001-03-29", "hungarian", "male", "kis street 41");
    mockMvc.perform(get("/api/users")
                    .principal(userAuth4)
                    .contentType(contentType)
                    .content(mapper.writeValueAsString(newUser)))
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnauthorized())
            .andExpect(jsonPath("$.status", Matchers.is("error")))
            .andExpect(jsonPath("$.message", Matchers.is("No such user.")));
  }

  @Test
  public void editUserDetails_should_ReturnError_when_PhoneNumberAlreadyExist() throws Exception {
    NewUserDetailsRequestDTO newUser = new NewUserDetailsRequestDTO("Mark", "Szabo",
            "szabotemple.oliver2001@gmail.com", "123456789",
            "2001-03-29", "hungarian", "male", "kis street 41");
    mockMvc.perform(put("/api/users")
                    .principal(userAuth2)
                    .contentType(contentType)
                    .content(mapper.writeValueAsString(newUser)))
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(status().isConflict())
            .andExpect(jsonPath("$.status", Matchers.is("error")))
            .andExpect(jsonPath("$.message", Matchers.is("This phone number is already exists!")));
  }

  @Test
  public void editUserDetails_should_ReturnError_when_EmailAlreadyExist() throws Exception {
    NewUserDetailsRequestDTO newUser = new NewUserDetailsRequestDTO("Mark", "Szabo",
            "booking.2024.test@gmail.com", "12345678910",
            "2001-03-29", "hungarian", "male", "kis street 41");
    mockMvc.perform(put("/api/users")
                    .principal(userAuth2)
                    .contentType(contentType)
                    .content(mapper.writeValueAsString(newUser)))
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(status().isConflict())
            .andExpect(jsonPath("$.status", Matchers.is("error")))
            .andExpect(jsonPath("$.message", Matchers.is("This email is already exists!")));
  }

  @Test
  public void editUserDetails_should_ReturnTheEditedUser() throws Exception {
    NewUserDetailsRequestDTO newUser = new NewUserDetailsRequestDTO("Mark", "Szabo",
            "szabotemple.oliver2001@gmail.com", "12345678910",
            "2001-03-29", "hungarian", "male", "kis street 41");
    mockMvc.perform(put("/api/users")
                    .principal(userAuth2)
                    .contentType(contentType)
                    .content(mapper.writeValueAsString(newUser)))
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(101))
            .andExpect(jsonPath("$.firstName").value("Mark"))
            .andExpect(jsonPath("$.lastName").value("Szabo"))
            .andExpect(jsonPath("$.username").value("Oli2"))
            .andExpect(jsonPath("$.email").value("szabotemple.oliver2001@gmail.com"))
            .andExpect(jsonPath("$.phoneNumber").value("12345678910"))
            .andExpect(jsonPath("$.nationality").value("hungarian"))
            .andExpect(jsonPath("$.address").value("kis street 41"));
  }

  @Test
  public void deleteUser_should_ReturnError_when_UserIdNotFound() throws Exception {
    mockMvc.perform(delete("/api/users")
                    .principal(userAuth4))
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnauthorized())
            .andExpect(jsonPath("$.status", Matchers.is("error")))
            .andExpect(jsonPath("$.message", Matchers.is("No such user.")));
  }

  @Test
  public void deleteUser_should_ReturnTheDeletedUser() throws Exception {
    mockMvc.perform(delete("/api/users")
                    .principal(userAuth3))
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(105))
            .andExpect(jsonPath("$.username").value("deletableUser"))
            .andExpect(jsonPath("$.email").value("deletableuser@gmail.com"))
            .andExpect(jsonPath("$.phoneNumber").value("12345678913"));
  }


}
