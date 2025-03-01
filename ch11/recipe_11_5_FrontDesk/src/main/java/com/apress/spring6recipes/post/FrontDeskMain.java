package com.apress.spring6recipes.post;

import com.apress.spring6recipes.post.config.FrontOfficeConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class FrontDeskMain {

  public static void main(String[] args) {
    var cfg = FrontOfficeConfiguration.class;
    try (var context = new AnnotationConfigApplicationContext(cfg)) {
      var frontDesk = context.getBean(FrontDesk.class);
      frontDesk.sendMail(new Mail("1234", "US", 1.5));
    }
  }
}
