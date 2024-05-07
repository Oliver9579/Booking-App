package com.example.booking.registration.controllers;

import com.example.booking.registration.models.RegistrationDTO;
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

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@Sql("/data.sql")
public class RegistrationControllerIT {

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
  public void registrationController_should_returnCorrectErrorMessage_when_EverythingAreMissing() throws Exception {
    mockMvc.perform(post("/register").contentType(contentType)
                    .content(mapper.writeValueAsString(new RegistrationDTO())))
            .andExpect(content().contentTypeCompatibleWith(contentType))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.status", is("error")))
            .andExpect(jsonPath("$.message", Matchers.allOf(
                    containsStringIgnoringCase("firstName"),
                    containsStringIgnoringCase("lastName"),
                    containsStringIgnoringCase("userName"),
                    containsStringIgnoringCase("email"),
                    containsStringIgnoringCase("password"),
                    containsStringIgnoringCase("phoneNumber"),
                    containsString("are required."))));
  }

  @Test
  public void registrationController_should_returnCorrectErrorMessage_when_givenEmailNotValid() throws Exception {
    mockMvc.perform(post("/register").contentType(contentType)
                    .content(mapper.writeValueAsString(new RegistrationDTO("Olivér", "Szabó", "Oli4",
                            "wrongEmail", "password123", "+361234567"))))
            .andExpect(content().contentTypeCompatibleWith(contentType))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.status", is("error")))
            .andExpect(jsonPath("$.message", is("Please provide a valid email address.")));
  }

  @Test
  public void registrationController_should_returnCorrectErrorMessage_when_givenPasswordNotValid() throws Exception {
    mockMvc.perform(post("/register").contentType(contentType)
                    .content(mapper.writeValueAsString(new RegistrationDTO("Szabó-Temple", "Olivér", "Oli4",
                            "oli@gmail.com", "sortpas", "+361234567"))))
            .andExpect(content().contentTypeCompatibleWith(contentType))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.status", is("error")))
            .andExpect(jsonPath("$.message", is("Password must have 8 characters.")));
  }

  @Test
  public void registrationController_should_returnCreatedUser_when_everyInputAreCorrect() throws Exception {
    mockMvc.perform(post("/register").contentType(contentType)
                    .content(mapper.writeValueAsString(new RegistrationDTO("Szabó-Temple", "Olivér", "Oli4",
                            "oli@gmail.com", "password", "+361234567"))))
            .andExpect(content().contentTypeCompatibleWith(contentType))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.userName").value("Oli4"))
            .andExpect(jsonPath("$.email").value("oli@gmail.com"))
            .andExpect(jsonPath("$.phoneNumber").value("+361234567"));
  }

}