package io.github.flynn.auto.testing.tutorial.infrastructure;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import io.github.flynn.auto.testing.tutorial.configuration.MybatisConfiguration;
import io.github.flynn.auto.testing.tutorial.infrastructure.mapper.UserMapper;
import io.github.flynn.auto.testing.tutorial.models.User;
import io.github.flynn.polaris.test.containers.MySQLContainer;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@MybatisTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ImportAutoConfiguration(classes = MybatisConfiguration.class)
public class UserMapperTest {

  @Container
  private static final MySQLContainer MYSQL = new MySQLContainer()
      .withDatabaseName("test");

  @Autowired
  private UserMapper userMapper;

  @Test
  void simpleTest() {
    Optional<User> user = userMapper.findById("1");
    assertThat(user).isPresent();
    assertThat(user.get().getName()).isEqualTo("Bob");
  }
}
