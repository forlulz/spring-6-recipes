package com.apress.spring6recipes.sequence.config;

import com.apress.spring6recipes.sequence.Sequence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class SequenceConfiguration {

  @Bean
  @DependsOn("datePrefixGenerator")
  public Sequence sequenceGenerator() {
    return new Sequence("A", 100000);
  }
}
