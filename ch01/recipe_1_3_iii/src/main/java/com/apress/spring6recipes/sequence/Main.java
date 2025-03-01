package com.apress.spring6recipes.sequence;

import com.apress.spring6recipes.sequence.config.SequenceConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext(SequenceConfiguration.class);

    Sequence generator = context.getBean(Sequence.class);

    System.out.println(generator.getSequence());
    System.out.println(generator.getSequence());
  }

}
