package com.apress.spring6recipes.bookshop.config;

import com.apress.spring6recipes.bookshop.BookShop;
import com.apress.spring6recipes.bookshop.JdbcBookShop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class BookstoreConfiguration {

  @Bean
  public DriverManagerDataSource dataSource() {
    var dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(org.postgresql.Driver.class.getName());
    dataSource.setUrl("jdbc:postgresql://localhost:5432/bookstore");
    dataSource.setUsername("postgres");
    dataSource.setPassword("password");
    return dataSource;
  }

  @Bean
  public BookShop bookShop(DataSource dataSource) {
    return new JdbcBookShop(dataSource);
  }

}
