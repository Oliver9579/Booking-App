package com.example.booking.hotel.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Sql("/data.sql")
public class HotelControllerIT {

  @Autowired
  private MockMvc mockMvc;
  private final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
          MediaType.APPLICATION_JSON.getSubtype(),
          StandardCharsets.UTF_8);
  private ObjectMapper mapper;

  @Before
  public void setup() {
    mapper = new ObjectMapper();
  }

  @Test
  public void getAllHotel_should_ReturnAllHotels() throws Exception {
    mockMvc.perform(get("/api/hotels/all"))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$", hasSize(greaterThan(0))));
  }

  @Test
  public void getHotelsByGivenDetails_should_ReturnError_when_NoHotelFound() throws Exception {
    String location = "Las Vegas";
    String checkInDate = "2024-06-14 14:00";
    String checkOutDate = "2024-06-16 10:00";
    Integer guests = 2;

    mockMvc.perform(get("/api/hotels")
                    .param("location", location)
                    .param("checkInDate", checkInDate)
                    .param("checkOutDate", checkOutDate)
                    .param("guests", String.valueOf(guests)))
            .andExpect(status().isNotFound())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.status", Matchers.is("error")))
            .andExpect(jsonPath("$.message", Matchers.is("There is no hotel at the specified location and time!")));
  }

  @Test
  public void getHotelsByGivenDetails_should_ReturnError_when_DatesAreTheSame() throws Exception {
    String location = "New York";
    String checkInDate = "2024-06-16 14:00";
    String checkOutDate = "2024-06-16 10:00";
    Integer guests = 2;

    mockMvc.perform(get("/api/hotels")
                    .param("location", location)
                    .param("checkInDate", checkInDate)
                    .param("checkOutDate", checkOutDate)
                    .param("guests", String.valueOf(guests)))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.status", Matchers.is("error")))
            .andExpect(jsonPath("$.message", Matchers.is("The two given dates is same!")));
  }

  @Test
  public void getHotelsByGivenDetails_should_ReturnHotels() throws Exception {
    String location = "New York";
    String checkInDate = "2024-06-14 14:00";
    String checkOutDate = "2024-06-16 10:00";
    Integer guests = 2;

    mockMvc.perform(get("/api/hotels")
                    .param("location", location)
                    .param("checkInDate", checkInDate)
                    .param("checkOutDate", checkOutDate)
                    .param("guests", String.valueOf(guests)))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.hotels").isArray())
            .andExpect(jsonPath("$.hotels", hasSize(greaterThan(0))));
  }

}
