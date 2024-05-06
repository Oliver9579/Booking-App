package com.example.booking.car.controllers;

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

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CarControllerIT {

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
  public void getCarsWithSameDropOffLocation_should_ReturnsCars() throws Exception {
    String pickUpLocation = "New York";
    String pickUpDate = "2024-06-05 10:00";
    String dropOffDate = "2024-06-10 10:00";

    mockMvc.perform(get("/api/cars/dropOff/same")
                    .param("pickUpLocation", pickUpLocation)
                    .param("pickUpDate", pickUpDate)
                    .param("dropOffDate", dropOffDate))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.cars").isArray())
            .andExpect(jsonPath("$.cars.length()").value(greaterThan(0)));
  }

  @Test
  public void getCarsWithSameDropOffLocation_should_ReturnsFilteredCars() throws Exception {
    String pickUpLocation = "New York";
    String pickUpDate = "2024-06-01 10:00";
    String dropOffDate = "2024-06-04 10:00";
    String carType = "MEDIUM";
    Integer capacity = 5;
    String transmissionType = "AUTOMATIC";

    mockMvc.perform(get("/api/cars/dropOff/same")
                    .param("pickUpLocation", pickUpLocation)
                    .param("pickUpDate", pickUpDate)
                    .param("dropOffDate", dropOffDate)
                    .param("carType", carType)
                    .param("capacity", String.valueOf(capacity))
                    .param("transmissionType", transmissionType))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.cars").isArray())
            .andExpect(jsonPath("$.cars.length()").value(greaterThan(0)))
            .andExpect(jsonPath("$.cars[*].type").value(everyItem(equalTo(carType))))
            .andExpect(jsonPath("$.cars[*].capacity").value(everyItem(equalTo(capacity))))
            .andExpect(jsonPath("$.cars[*].transmissionType").value(everyItem(equalTo(transmissionType))));
  }

  @Test
  public void getCarsWithSameDropOffLocation_should_ReturnError_whenDateIsSame() throws Exception {
    String pickUpLocation = "New York";
    String pickUpDate = "2024-06-05 10:00";
    String dropOffDate = "2024-06-05 10:00";

    mockMvc.perform(get("/api/cars/dropOff/same")
                    .param("pickUpLocation", pickUpLocation)
                    .param("pickUpDate", pickUpDate)
                    .param("dropOffDate", dropOffDate))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.status", Matchers.is("error")))
            .andExpect(jsonPath("$.message", Matchers.is("The two given dates is same!")));
  }

  @Test
  public void getCarsWithSameDropOffLocation_should_ReturnError_whenNoCarFound() throws Exception {
    String pickUpLocation = "Las Vegas";
    String pickUpDate = "2024-06-05 10:00";
    String dropOffDate = "2024-06-10 10:00";

    mockMvc.perform(get("/api/cars/dropOff/same")
                    .param("pickUpLocation", pickUpLocation)
                    .param("pickUpDate", pickUpDate)
                    .param("dropOffDate", dropOffDate))
            .andExpect(status().isNotFound())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.status", Matchers.is("error")))
            .andExpect(jsonPath("$.message", Matchers.is("There is no cars found with the specified data.")));
  }

  @Test
  public void getCarsWithSameDropOffLocation_should_ReturnError_whenNoAvailableCarFound() throws Exception {
    String pickUpLocation = "New York";
    String pickUpDate = "2024-06-10 10:00";
    String dropOffDate = "2024-06-15 10:00";
    String carType = "MEDIUM";
    Integer capacity = 5;
    String transmissionType = "AUTOMATIC";

    mockMvc.perform(get("/api/cars/dropOff/same")
                    .param("pickUpLocation", pickUpLocation)
                    .param("pickUpDate", pickUpDate)
                    .param("dropOffDate", dropOffDate)
                    .param("carType", carType)
                    .param("capacity", String.valueOf(capacity))
                    .param("transmissionType", transmissionType))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.status", Matchers.is("error")))
            .andExpect(jsonPath("$.message", Matchers.is("There is no cars available for the specified dates.")));
  }

  @Test
  public void getCarsDifferentDropOffLocation_should_ReturnsCars() throws Exception {
    String pickUpLocation = "Chicago";
    String dropOffLocation = "Miami";
    String pickUpDate = "2024-06-05 10:00";
    String dropOffDate = "2024-06-10 10:00";

    mockMvc.perform(get("/api/cars/dropOff/different")
                    .param("pickUpLocation", pickUpLocation)
                    .param("dropOffLocation", dropOffLocation)
                    .param("pickUpDate", pickUpDate)
                    .param("dropOffDate", dropOffDate))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.cars").isArray())
            .andExpect(jsonPath("$.cars.length()").value(greaterThan(0)));
  }

  @Test
  public void getCarsDifferentDropOffLocation_should_ReturnsFilteredCars() throws Exception {
    String pickUpLocation = "Chicago";
    String dropOffLocation = "Miami";
    String pickUpDate = "2024-06-01 10:00";
    String dropOffDate = "2024-06-04 10:00";
    String carType = "MEDIUM";
    Integer capacity = 5;
    String transmissionType = "MANUAL";

    mockMvc.perform(get("/api/cars/dropOff/different")
                    .param("pickUpLocation", pickUpLocation)
                    .param("dropOffLocation", dropOffLocation)
                    .param("pickUpDate", pickUpDate)
                    .param("dropOffDate", dropOffDate)
                    .param("carType", carType)
                    .param("capacity", String.valueOf(capacity))
                    .param("transmissionType", transmissionType))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.cars").isArray())
            .andExpect(jsonPath("$.cars.length()").value(greaterThan(0)))
            .andExpect(jsonPath("$.cars[*].type").value(everyItem(equalTo(carType))))
            .andExpect(jsonPath("$.cars[*].capacity").value(everyItem(equalTo(capacity))))
            .andExpect(jsonPath("$.cars[*].transmissionType").value(everyItem(equalTo(transmissionType))));
  }

  @Test
  public void getCarsDifferentDropOffLocation_should_ReturnError_whenDateIsSame() throws Exception {
    String pickUpLocation = "Chicago";
    String dropOffLocation = "Miami";
    String pickUpDate = "2024-06-05 10:00";
    String dropOffDate = "2024-06-05 10:00";

    mockMvc.perform(get("/api/cars/dropOff/different")
                    .param("pickUpLocation", pickUpLocation)
                    .param("dropOffLocation", dropOffLocation)
                    .param("pickUpDate", pickUpDate)
                    .param("dropOffDate", dropOffDate))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.status", Matchers.is("error")))
            .andExpect(jsonPath("$.message", Matchers.is("The two given dates is same!")));
  }

  @Test
  public void getCarsDifferentDropOffLocation_should_ReturnError_whenNoCarFound() throws Exception {
    String pickUpLocation = "Las Vegas";
    String dropOffLocation = "Miami";
    String pickUpDate = "2024-06-05 10:00";
    String dropOffDate = "2024-06-10 10:00";

    mockMvc.perform(get("/api/cars/dropOff/different")
                    .param("pickUpLocation", pickUpLocation)
                    .param("dropOffLocation", dropOffLocation)
                    .param("pickUpDate", pickUpDate)
                    .param("dropOffDate", dropOffDate))
            .andExpect(status().isNotFound())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.status", Matchers.is("error")))
            .andExpect(jsonPath("$.message", Matchers.is("There is no cars found with the specified data.")));
  }

  @Test
  public void getCarsDifferentDropOffLocation_should_ReturnError_whenNoAvailableCarFound() throws Exception {
    String pickUpLocation = "Chicago";
    String dropOffLocation = "Miami";
    String pickUpDate = "2024-06-10 10:00";
    String dropOffDate = "2024-06-15 10:00";
    String carType = "MEDIUM";
    Integer capacity = 5;
    String transmissionType = "MANUAL";

    mockMvc.perform(get("/api/cars/dropOff/different")
                    .param("pickUpLocation", pickUpLocation)
                    .param("dropOffLocation", dropOffLocation)
                    .param("pickUpDate", pickUpDate)
                    .param("dropOffDate", dropOffDate)
                    .param("carType", carType)
                    .param("capacity", String.valueOf(capacity))
                    .param("transmissionType", transmissionType))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.status", Matchers.is("error")))
            .andExpect(jsonPath("$.message", Matchers.is("There is no cars available for the specified dates.")));
  }

}
