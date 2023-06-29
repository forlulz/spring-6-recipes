package com.apress.spring6recipes.sequence.config;

import com.apress.spring6recipes.sequence.Sequence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SequenceConfiguration {

  @Bean
  public Sequence sequence() {
    var seqgen = new Sequence();
    seqgen.setPrefix("30");
    seqgen.setSuffix("A");
    seqgen.setInitial(100000);
    return seqgen;
  }
}
