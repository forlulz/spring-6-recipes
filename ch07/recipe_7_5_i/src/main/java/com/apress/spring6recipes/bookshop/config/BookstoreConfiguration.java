package com.apress.spring6recipes.bookshop.config;

import com.apress.spring6recipes.bookshop.JdbcBookShop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
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
  public DataSourceTransactionManager transactionManager(DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }

  @Bean
  public TransactionTemplate transactionTemplate(PlatformTransactionManager transactionManager) {
    var transactionTemplate = new TransactionTemplate();
    transactionTemplate.setTransactionManager(transactionManager);
    return transactionTemplate;
  }

  @Bean
  public JdbcBookShop bookShop(DataSource dataSource) {
    return new JdbcBookShop(dataSource);
  }

}
