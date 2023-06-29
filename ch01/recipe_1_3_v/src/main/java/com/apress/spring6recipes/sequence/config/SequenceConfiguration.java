package com.apress.spring6recipes.sequence.config;

import com.apress.spring6recipes.sequence.Sequence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SequenceConfiguration {

  @Bean
  public Sequence sequence() {
    var sequence = new Sequence();
    sequence.setInitial(100000);
    sequence.setSuffix("A");
    return sequence;
  }

}
