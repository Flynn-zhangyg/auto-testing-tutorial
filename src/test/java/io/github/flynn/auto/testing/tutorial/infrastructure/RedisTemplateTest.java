package io.github.flynn.auto.testing.tutorial.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.flynn.auto.testing.tutorial.apps.NoDataSourceApplication;
import io.github.flynn.polaris.test.containers.RedisContainer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@ExtendWith(SpringExtension.class)
@Testcontainers
@SpringBootTest(classes = NoDataSourceApplication.class)
public class RedisTemplateTest {

  @Container
  private static final RedisContainer REDIS = new RedisContainer();

  @Autowired
  private StringRedisTemplate redisTemplate;

  @BeforeAll
  static void beforeAll() {
    REDIS.withEnv("spring.data.redis.host", REDIS.getHost());
    REDIS.withEnv("spring.data.redis.port", String.valueOf(REDIS.getFirstMappedPort()));
  }

  @Test
  void simpleTest() {
    redisTemplate.opsForValue().set("key", "value");
    assertThat(redisTemplate.opsForValue().get("key")).isEqualTo("value");
  }
}
