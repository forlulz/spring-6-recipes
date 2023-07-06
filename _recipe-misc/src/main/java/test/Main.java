package test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class Main {
  public static void main(String[] args) {
    var context = new AnnotationConfigApplicationContext(Main.class);
    var repository = context.getBean(FailRepository.class);
    try{
      repository.fail();
    } catch (Exception e) {
      e.printStackTrace(); // Not wrapped up as DataAccessException (p.7)
    }
  }
}
