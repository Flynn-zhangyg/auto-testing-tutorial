package io.github.flynn.auto.testing.tutorial.controllers;

import io.github.flynn.auto.testing.tutorial.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/hello")
  public String hello(@RequestParam("name") String name) {
    return userService.hello(name);
  }
}
