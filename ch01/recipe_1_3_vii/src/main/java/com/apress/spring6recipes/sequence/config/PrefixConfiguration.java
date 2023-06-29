package com.apress.spring6recipes.sequence.config;

import com.apress.spring6recipes.sequence.DatePrefixGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PrefixConfiguration {

  @Bean
  public DatePrefixGenerator datePrefixGenerator() {
    return new DatePrefixGenerator("yyyyMMdd");
  }
}
