package com.apress.spring6recipes.shop;

import com.apress.spring6recipes.shop.config.ShopConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

  public static void main(String[] args) {
    var cfg = ShopConfiguration.class;
    try (var ctx = new AnnotationConfigApplicationContext(cfg)) {
      var aaa = ctx.getBean("aaa", Product.class);
      var cdrw = ctx.getBean("cdrw", Product.class);
      System.out.println(aaa);
      System.out.println(cdrw);
    }
  }
}
