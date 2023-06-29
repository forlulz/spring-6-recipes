package com.apress.spring6recipes.shop.config;

import com.apress.spring6recipes.shop.Product;
import com.apress.spring6recipes.shop.ProductCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.apress.spring6recipes.shop")
public class ShopConfiguration {

  @Bean
  public Product aaa() {
    return ProductCreator.createProduct("aaa");
  }

  @Bean
  public Product cdrw() {
    return ProductCreator.createProduct("cdrw");
  }

  @Bean
  public Product dvdrw() {
    return ProductCreator.createProduct("dvdrw");
  }
}
