package com.apress.spring6recipes.post.config;

import com.apress.spring6recipes.post.FrontDeskImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class FrontOfficeConfiguration {

  @Bean
  public FrontDeskImpl frontDesk() {
    return new FrontDeskImpl();
  }

}
