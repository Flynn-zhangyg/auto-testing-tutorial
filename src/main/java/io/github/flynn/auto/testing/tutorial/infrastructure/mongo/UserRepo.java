package io.github.flynn.auto.testing.tutorial.infrastructure.mongo;

import io.github.flynn.auto.testing.tutorial.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<User, String> {
}
