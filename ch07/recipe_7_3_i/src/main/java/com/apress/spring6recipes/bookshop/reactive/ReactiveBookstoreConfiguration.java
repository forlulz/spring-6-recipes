package com.apress.spring6recipes.bookshop.reactive;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.transaction.ReactiveTransactionManager;

import static io.r2dbc.spi.ConnectionFactoryOptions.*;

@Configuration
public class ReactiveBookstoreConfiguration {

  @Bean
  public ConnectionFactoryInitializer initializer(ConnectionFactory cf) {
    var initializer = new ConnectionFactoryInitializer();
    initializer.setConnectionFactory(cf);
    initializer.setDatabasePopulator(new ResourceDatabasePopulator(
      new ClassPathResource("/bookstore.sql")));
    return initializer;
  }

  @Bean
  public ConnectionFactory connectionFactory() {
    var options = ConnectionFactoryOptions.builder()
      .option(DRIVER, "postgresql")
      .option(HOST, "localhost")
      .option(PORT, 5432)
      .option(DATABASE, "bookstore")
      .option(USER, "postgres")
      .option(PASSWORD, "password")
      .build();
    return ConnectionFactories.get(options);
  }

  @Bean
  public R2dbcTransactionManager transactionManager(ConnectionFactory cf) {
    return new R2dbcTransactionManager(cf);
  }

  @Bean
  public TransactionalR2dbcBookShop bookShop(ReactiveTransactionManager txManager, ConnectionFactory cf) {
    return new TransactionalR2dbcBookShop(txManager, cf);
  }
}
