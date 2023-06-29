package com.apress.spring6recipes.bookshop.reactive;

import com.apress.spring6recipes.bookshop.config.BookstoreConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.CountDownLatch;

public class Main {

  public static void main(String[] args) throws Throwable {
    var cfg = BookstoreConfiguration.class;
    try (var context = new AnnotationConfigApplicationContext(cfg)) {
      var bookShop = context.getBean(BookShop.class);
      var counter = new CountDownLatch(1);
      bookShop.purchase("0001", "user1").subscribe((x) -> counter.countDown());
      counter.await();
    }
  }
}
