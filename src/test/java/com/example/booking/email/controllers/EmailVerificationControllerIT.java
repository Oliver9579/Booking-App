package com.example.booking.email.controllers;

import com.example.booking.exceptions.VerificationTokenNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmailVerificationControllerIT {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void verify_should_returnCorrectResponseEntity() throws Exception {
    mockMvc.perform(get("/verify?token=123456789"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.username", is("PlayerToBeVerified")))
            .andExpect(jsonPath("$.enabled", is(true)));
  }

  @Test
  public void verify_should_returnCorrectHttpResponse_when_verificationTokenIsNotFound() throws Exception {
    mockMvc.perform(get("/verify?token=-1"))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.status", is("error")))
            .andExpect(jsonPath("$.message", is(VerificationTokenNotFoundException.MESSAGE)));
  }


}
