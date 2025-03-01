package com.apress.spring6recipes.course;

import com.apress.spring6recipes.course.config.CourseConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;

public class Main {

  public static void main(String[] args) {

    try (var context = new AnnotationConfigApplicationContext(CourseConfiguration.class)) {
      var courseDao = context.getBean(CourseRepository.class);

      var course = new Course();
      course.setTitle("Core Spring");
      course.setBeginDate(LocalDate.of(2007, 8, 1));
      course.setEndDate(LocalDate.of(2007, 9, 1));
      course.setFee(1000);

      System.out.println("\nCourse before persisting");
      System.out.println(course);

      var persisted = courseDao.save(course);

      System.out.println("\nCourse after persisting");
      System.out.println(persisted);

      var courseId = persisted.getId();

      System.out.println("\nCourse fresh from database");
      courseDao.findById(courseId).ifPresent(System.out::println);

      courseDao.deleteById(courseId);
    }

  }

}
