package io.github.flynn.auto.testing.tutorial.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "io.github.flynn.auto.testing.tutorial.infrastructure")
public class MybatisConfiguration {
}
