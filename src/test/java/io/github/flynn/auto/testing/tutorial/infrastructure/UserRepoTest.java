package io.github.flynn.auto.testing.tutorial.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import io.github.flynn.auto.testing.tutorial.apps.NoDataSourceApplication;
import io.github.flynn.auto.testing.tutorial.infrastructure.mongo.UserRepo;
import io.github.flynn.auto.testing.tutorial.models.User;
import io.github.flynn.polaris.test.containers.MongoDBContainer;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(classes = NoDataSourceApplication.class)
@Testcontainers
@DataMongoTest
class UserRepoTest {

  @Container
  private static final MongoDBContainer MONGO = new MongoDBContainer();

  @Autowired
  private UserRepo userRepo;

  @Test
  void simpleTest() {
    User bob = User.builder().name("Bob").build();
    userRepo.insert(bob);

    Optional<User> user = userRepo.findById(bob.getId());
    assertThat(user).isPresent();
    assertThat(user.get().getName()).isEqualTo("Bob");
  }
}