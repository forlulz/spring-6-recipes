package com.apress.spring6recipes.court.web.config;

import com.apress.spring6recipes.court.domain.Member;
import com.apress.spring6recipes.court.domain.Members;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.xml.MarshallingView;

import java.util.Map;

import static jakarta.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.apress.spring6recipes.court")
public class CourtRestConfiguration {

  @Bean
  public MarshallingView membertemplate(Marshaller marshaller) {
    return new MarshallingView(marshaller);
  }

  @Bean
  public Jaxb2Marshaller jaxb2Marshaller() {
    var marshaller = new Jaxb2Marshaller();
    marshaller.setClassesToBeBound(Member.class, Members.class);
    marshaller.setMarshallerProperties(Map.of(JAXB_FORMATTED_OUTPUT, Boolean.TRUE));
    return marshaller;
  }

  @Bean
  public BeanNameViewResolver viewResolver() {
    return new BeanNameViewResolver();
  }
}
