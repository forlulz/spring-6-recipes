package com.apress.spring6recipes.sequence;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Primary
public class DatePrefixGenerator implements PrefixGenerator {

  private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

  public String getPrefix() {
    return formatter.format(LocalDateTime.now());
  }

}
