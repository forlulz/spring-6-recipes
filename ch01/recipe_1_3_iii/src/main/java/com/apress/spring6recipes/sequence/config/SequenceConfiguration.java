package com.apress.spring6recipes.sequence.config;

import com.apress.spring6recipes.sequence.DatePrefixGenerator;
import com.apress.spring6recipes.sequence.NumberPrefixGenerator;
import com.apress.spring6recipes.sequence.Sequence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SequenceConfiguration {

  @Bean
  public DatePrefixGenerator datePrefixGenerator() {
    return new DatePrefixGenerator("yyyyMMdd");
  }

  @Bean
  public NumberPrefixGenerator numberPrefixGenerator() {
    return new NumberPrefixGenerator();
  }

  @Bean
  public Sequence sequenceGenerator() {
    return new Sequence("A", 100000);
  }

}
