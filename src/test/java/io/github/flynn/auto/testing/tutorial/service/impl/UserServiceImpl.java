package io.github.flynn.auto.testing.tutorial.service.impl;

import io.github.flynn.auto.testing.tutorial.infrastructure.mapper.UserMapper;
import io.github.flynn.auto.testing.tutorial.models.User;
import io.github.flynn.auto.testing.tutorial.service.UserService;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserMapper userMapper;

  @Override
  public void consume(User user) {

  }

  @Override
  public String hello(String name) {
    Optional<User> user = userMapper.findById(name);
    return user.map(User::getName).orElse(null);
  }
}
