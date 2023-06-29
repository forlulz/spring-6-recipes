package com.apress.spring6recipes.post.config;

import com.apress.spring6recipes.post.BackOfficeImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BackOfficeConfiguration {

  @Bean
  public BackOfficeImpl backOffice() {
    return new BackOfficeImpl();
  }
}
