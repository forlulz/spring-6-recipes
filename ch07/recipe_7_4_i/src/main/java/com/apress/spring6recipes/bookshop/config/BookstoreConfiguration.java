package com.apress.spring6recipes.bookshop.config;

import com.apress.spring6recipes.bookshop.TransactionalJdbcBookShop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

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
  public DataSourceTransactionManager transactionManager(DataSource ds) {
    return new DataSourceTransactionManager(ds);
  }

  @Bean
  public TransactionalJdbcBookShop bookShop(PlatformTransactionManager ptm,
                                            DataSource dataSource) {
    return new TransactionalJdbcBookShop(ptm, dataSource);
  }
}
