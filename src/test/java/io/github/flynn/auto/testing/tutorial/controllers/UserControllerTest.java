package io.github.flynn.auto.testing.tutorial.controllers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import io.github.flynn.auto.testing.tutorial.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
@ExtendWith(SpringExtension.class)
@Import(UserController.class)
public class UserControllerTest {

  @MockBean
  private UserService userService;

  @Autowired
  private MockMvc mockMvc;

  @Test
  void helloTest() throws Exception{

    when(userService.hello("Bob")).thenReturn("Hello, Bob!");

    mockMvc.perform(get("/user/hello")
        .queryParam("name", "Bob"))
        .andExpect(status().isOk())
        .andExpect(content().string("Hello, Bob!"));

    verify(userService).hello("Bob");
  }
}
