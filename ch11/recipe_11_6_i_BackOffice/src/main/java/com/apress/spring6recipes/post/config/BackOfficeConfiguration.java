package com.apress.spring6recipes.post.config;

import com.apress.spring6recipes.post.BackOffice;
import com.apress.spring6recipes.post.BackOfficeImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by marten on 02-06-14.
 */
@Configuration
public class BackOfficeConfiguration {

  @Bean
  public BackOffice backOffice() {
    return new BackOfficeImpl();
  }

}
