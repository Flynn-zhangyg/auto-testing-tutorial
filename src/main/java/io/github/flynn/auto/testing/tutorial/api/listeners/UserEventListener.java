package io.github.flynn.auto.testing.tutorial.api.listeners;

import io.github.flynn.auto.testing.tutorial.models.User;
import io.github.flynn.auto.testing.tutorial.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class UserEventListener {

  private final UserService userService;

  @KafkaListener(topics = "test")
  public void consume(User user) {
    log.info("Received user event: {}.", user);
    try {
      userService.consume(user);
    } catch (Exception e) {
      log.error("Unexpected exception caught when consume user event: {}.", user);
    }
    log.info("Handled user event: {}.", user);
  }
}
