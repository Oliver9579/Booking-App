package com.example.booking.review.controllers;

import com.example.booking.review.DTOs.ReviewRequestDTO;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@Sql("/data.sql")
public class ReviewControllerIT {

  @Autowired
  private MockMvc mockMvc;
  private final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
          MediaType.APPLICATION_JSON.getSubtype(),
          StandardCharsets.UTF_8);
  private ObjectMapper mapper;
  private User user1;
  private User user2;
  private Authentication userAuth1;
  private Authentication userAuth2;

  @Before
  public void setup() {
    mapper = new ObjectMapper();
    user1 = new User(100, "", "", "Oli", "booking.2024.test@gmail.com",
            "Oli12345", "123456789");
    user2 = new User(0, "", "", "", "",
            "", "");
    userAuth1 = new UsernamePasswordAuthenticationToken(user1, null, null);
    userAuth2 = new UsernamePasswordAuthenticationToken(user2, null, null);
  }

  @Test
  public void saveCarReview_should_ReturnTheSavedReview() throws Exception {
    ReviewRequestDTO newCarReview = new ReviewRequestDTO("nagyon jó");
    mockMvc.perform(post("/api/reviews/car/3")
                    .principal(userAuth1)
                    .contentType(contentType)
                    .content(mapper.writeValueAsString(newCarReview)))
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.username").value("Oli"))
            .andExpect(jsonPath("$.comment").value("nagyon jó"));
  }

  @Test
  public void saveCarReview_should_ReturnError_when_UserIdNotFound() throws Exception {
    ReviewRequestDTO newCarReview = new ReviewRequestDTO("nagyon jó");
    mockMvc.perform(post("/api/reviews/car/3")
                    .principal(userAuth2)
                    .contentType(contentType)
                    .content(mapper.writeValueAsString(newCarReview)))
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnauthorized())
            .andExpect(jsonPath("$.status", Matchers.is("error")))
            .andExpect(jsonPath("$.message", Matchers.is("No such user.")));
  }

  @Test
  public void saveCarReview_should_ReturnError_when_CarIdNotFound() throws Exception {
    ReviewRequestDTO newCarReview = new ReviewRequestDTO("nagyon jó");
    mockMvc.perform(post("/api/reviews/car/0")
                    .principal(userAuth1)
                    .contentType(contentType)
                    .content(mapper.writeValueAsString(newCarReview)))
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.status", Matchers.is("error")))
            .andExpect(jsonPath("$.message", Matchers.is("Id not found")));
  }

  @Test
  public void saveHotelReview_should_ReturnTheSavedReview() throws Exception {
    ReviewRequestDTO newHotelReview = new ReviewRequestDTO("nagyon jó");
    mockMvc.perform(post("/api/reviews/hotel/3")
                    .principal(userAuth1)
                    .contentType(contentType)
                    .content(mapper.writeValueAsString(newHotelReview)))
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.username").value("Oli"))
            .andExpect(jsonPath("$.comment").value("nagyon jó"));
  }

  @Test
  public void saveHotelReview_should_ReturnError_when_UserIdNotFound() throws Exception {
    ReviewRequestDTO newHotelReview = new ReviewRequestDTO("nagyon jó");
    mockMvc.perform(post("/api/reviews/hotel/3")
                    .principal(userAuth2)
                    .contentType(contentType)
                    .content(mapper.writeValueAsString(newHotelReview)))
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnauthorized())
            .andExpect(jsonPath("$.status", Matchers.is("error")))
            .andExpect(jsonPath("$.message", Matchers.is("No such user.")));
  }

  @Test
  public void saveHotelReview_should_ReturnError_when_HotelIdNotFound() throws Exception {
    ReviewRequestDTO newHotelReview = new ReviewRequestDTO("nagyon jó");
    mockMvc.perform(post("/api/reviews/hotel/0")
                    .principal(userAuth1)
                    .contentType(contentType)
                    .content(mapper.writeValueAsString(newHotelReview)))
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.status", Matchers.is("error")))
            .andExpect(jsonPath("$.message", Matchers.is("Id not found")));
  }

  @Test
  public void saveFlightReview_should_ReturnTheSavedReview() throws Exception {
    ReviewRequestDTO newFlightReview = new ReviewRequestDTO("nagyon jó");
    mockMvc.perform(post("/api/reviews/flight/3")
                    .principal(userAuth1)
                    .contentType(contentType)
                    .content(mapper.writeValueAsString(newFlightReview)))
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.username").value("Oli"))
            .andExpect(jsonPath("$.comment").value("nagyon jó"));
  }

  @Test
  public void saveFlightReview_should_ReturnError_when_UserIdNotFound() throws Exception {
    ReviewRequestDTO newFlightReview = new ReviewRequestDTO("nagyon jó");
    mockMvc.perform(post("/api/reviews/flight/3")
                    .principal(userAuth2)
                    .contentType(contentType)
                    .content(mapper.writeValueAsString(newFlightReview)))
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnauthorized())
            .andExpect(jsonPath("$.status", Matchers.is("error")))
            .andExpect(jsonPath("$.message", Matchers.is("No such user.")));
  }

  @Test
  public void saveFlightReview_should_ReturnError_when_FlightIdNotFound() throws Exception {
    ReviewRequestDTO newFlightReview = new ReviewRequestDTO("nagyon jó");
    mockMvc.perform(post("/api/reviews/flight/0")
                    .principal(userAuth1)
                    .contentType(contentType)
                    .content(mapper.writeValueAsString(newFlightReview)))
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.status", Matchers.is("error")))
            .andExpect(jsonPath("$.message", Matchers.is("Id not found")));
  }

  @Test
  public void getCarAllReviews_should_ReturnError_when_CarIdNotFound() throws Exception {
    mockMvc.perform(get("/api/reviews/car/0"))
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.status", Matchers.is("error")))
            .andExpect(jsonPath("$.message", Matchers.is("Id not found")));
  }

  @Test
  public void getCarAllReviews_should_ReturnReviews() throws Exception {
    mockMvc.perform(get("/api/reviews/car/1"))
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray());
  }

  @Test
  public void getHotelAllReviews_should_ReturnError_when_HotelIdNotFound() throws Exception {
    mockMvc.perform(get("/api/reviews/hotel/0"))
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.status", Matchers.is("error")))
            .andExpect(jsonPath("$.message", Matchers.is("Id not found")));
  }

  @Test
  public void getHotelAllReviews_should_ReturnReviews() throws Exception {
    mockMvc.perform(get("/api/reviews/hotel/1"))
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray());
  }

  @Test
  public void getFlightAllReviews_should_ReturnError_when_FlightIdNotFound() throws Exception {
    mockMvc.perform(get("/api/reviews/flight/0"))
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.status", Matchers.is("error")))
            .andExpect(jsonPath("$.message", Matchers.is("Id not found")));
  }

  @Test
  public void getFlightAllReviews_should_ReturnReviews() throws Exception {
    mockMvc.perform(get("/api/reviews/flight/1"))
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray());
  }
}
