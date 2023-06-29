package com.apress.spring6recipes.shop.config;

import com.apress.spring6recipes.shop.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShopConfiguration {

  @Bean
  public static AuditCheckBeanPostProcessor auditCheckBeanPostProcessor() {
    return new AuditCheckBeanPostProcessor();
  }

  @Bean
  public static ProductCheckBeanPostProcessor productCheckBeanPostProcessor() {
    return new ProductCheckBeanPostProcessor();
  }

  @Bean
  public Product aaa() {
    return new Battery("AAA", 2.5, true);
  }

  @Bean
  public Product cdrw() {
    return new Disc("CD-RW", 1.5, 700);
  }

  @Bean
  public Product dvdrw() {
    return new Disc("DVD-RW", 3.0, 4700);
  }

  @Bean
  public Cashier cashier() {
    var path = System.getProperty("java.io.tmpdir") + "/cashier";
    return new Cashier("checkout", path);
  }


}
