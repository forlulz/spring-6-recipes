package com.apress.spring6recipes.bookshop;

import com.apress.spring6recipes.bookshop.config.BookstoreConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {

  public static void main(String[] args) {
    var cfg = BookstoreConfiguration.class;
    try (var context = new AnnotationConfigApplicationContext(cfg)) {
      var isbnList = List.of("0001", "0002");
      var cashier = context.getBean(Cashier.class);
      cashier.checkout(isbnList, "user1");
    }
  }
}
