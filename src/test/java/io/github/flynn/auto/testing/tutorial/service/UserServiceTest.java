package io.github.flynn.auto.testing.tutorial.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.github.flynn.auto.testing.tutorial.infrastructure.mapper.UserMapper;
import io.github.flynn.auto.testing.tutorial.models.User;
import io.github.flynn.auto.testing.tutorial.service.impl.UserServiceImpl;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
class UserServiceTest {
  private final UserMapper userMapper = Mockito.mock(UserMapper.class);

  private final UserService userService = new UserServiceImpl(userMapper);
  @Test
  void hello() {
    when(userMapper.findById("1")).thenReturn(Optional.of(User.builder().name("Bob").build()));

    String result = userService.hello("1");

    verify(userMapper).findById("1");
    assertThat(result).isEqualTo("Bob");
  }
}