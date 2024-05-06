package com.example.booking.flight.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FlightControllerIT {

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
  public void getFlights_should_ReturnFlights() throws Exception {
    mockMvc.perform(get("/api/flights"))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.flights").isArray())
            .andExpect(jsonPath("$.flights.length()").value(greaterThan(0)));
  }

  @Test
  public void getFlightsById_should_ReturnError_whenIdNotFound() throws Exception {
    mockMvc.perform(get("/api/flights/0"))
            .andExpect(status().isNotFound())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.status", Matchers.is("error")))
            .andExpect(jsonPath("$.message", Matchers.is("Id not found")));
  }

  @Test
  public void getFlightsById_should_ReturnAFlight() throws Exception {
    mockMvc.perform(get("/api/flights/1"))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.airline").value("Delta Airlines"))
            .andExpect(jsonPath("$.origin").value("New York"))
            .andExpect(jsonPath("$.originAirportCode").value("JFK"))
            .andExpect(jsonPath("$.destination").value("Los Angeles"))
            .andExpect(jsonPath("$.destinationAirportCode").value("LAX"))
            .andExpect(jsonPath("$.duration").value(360));
  }

  @Test
  public void getRoundTripFlights_should_ReturnError_when_DatesAreTheSame() throws Exception {
    String origin = "Chicago";
    String destination = "Miami";
    String departureDate = "2024-06-25";
    String returnDate = "2024-06-25";
    String returnOrigin = "Miami";
    String returnDestination = "Chicago";

    mockMvc.perform(get("/api/flights/return")
                    .param("origin", origin)
                    .param("destination", destination)
                    .param("departureDate", departureDate)
                    .param("returnDate", returnDate)
                    .param("returnOrigin", returnOrigin)
                    .param("returnDestination", returnDestination))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.status", Matchers.is("error")))
            .andExpect(jsonPath("$.message", Matchers.is("The two given dates is same!")));
  }
}
