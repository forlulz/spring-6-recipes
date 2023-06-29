package com.apress.spring6recipes.course.config;

import com.apress.spring6recipes.course.CourseDao;
import com.apress.spring6recipes.course.jpa.JpaCourseDao;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;

@Configuration
public class CourseConfiguration {

  @Bean
  public CourseDao courseDao(EntityManagerFactory entityManagerFactory) {
    return new JpaCourseDao(entityManagerFactory);
  }

  @Bean
  public LocalEntityManagerFactoryBean entityManagerFactory() {
    var emf = new LocalEntityManagerFactoryBean();
    emf.setPersistenceUnitName("course");
    return emf;
  }
}
