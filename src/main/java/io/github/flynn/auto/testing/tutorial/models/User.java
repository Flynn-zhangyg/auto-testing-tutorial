package io.github.flynn.auto.testing.tutorial.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document("user")
@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
public class User {

  @Id
  private String id;
  private String name;
}
