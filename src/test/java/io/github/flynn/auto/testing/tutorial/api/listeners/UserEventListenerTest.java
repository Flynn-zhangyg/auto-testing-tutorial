package io.github.flynn.auto.testing.tutorial.api.listeners;

import static org.mockito.Mockito.verify;
import static org.testcontainers.shaded.org.awaitility.Awaitility.waitAtMost;

import io.github.flynn.auto.testing.tutorial.apps.NoDataSourceApplication;
import io.github.flynn.auto.testing.tutorial.configuration.KafkaConfiguration;
import io.github.flynn.auto.testing.tutorial.models.User;
import io.github.flynn.auto.testing.tutorial.service.UserService;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(classes = NoDataSourceApplication.class)
@ExtendWith(SpringExtension.class)
@Import(UserEventListener.class)
@ImportAutoConfiguration(classes = KafkaConfiguration.class)
@EmbeddedKafka(
    partitions = 1,
    brokerProperties = {
        "listeners=PLAINTEXT://localhost:9092",
        "port=9092"
    })
@DirtiesContext
class UserEventListenerTest {

  @MockBean
  private UserService userService;

  @Autowired
  private KafkaTemplate<String, User> kafkaTemplate;

  @Test
  void simpleTest() {
    User bob = User.builder().name("Bob").build();
    kafkaTemplate.send("test", bob);

    waitAtMost(5, TimeUnit.SECONDS).untilAsserted(() -> {
      verify(userService).consume(bob);
    });
  }
}