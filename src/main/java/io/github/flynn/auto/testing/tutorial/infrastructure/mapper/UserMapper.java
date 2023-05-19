package io.github.flynn.auto.testing.tutorial.infrastructure.mapper;

import io.github.flynn.auto.testing.tutorial.models.User;
import java.util.Optional;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

  String TABLE_NAME = "test.user";

  @Select({
      "select * from",
      TABLE_NAME,
      "where id = #{id}"
  })
  Optional<User> findById(@Param("id") String id);
}
