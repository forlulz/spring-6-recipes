package com.apress.spring6recipes.vehicle.config;

import com.apress.spring6recipes.vehicle.R2dbcVehicleDao;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

import static io.r2dbc.spi.ConnectionFactoryOptions.*;

@Configuration
public class VehicleConfiguration {

  @Bean
  public R2dbcVehicleDao vehicleDao(ConnectionFactory cf) {
    return new R2dbcVehicleDao(cf);
  }

  @Bean
  public ConnectionFactory connectionFactory() {
    var options = ConnectionFactoryOptions.builder()
      .option(DRIVER, "postgresql")
      .option(HOST, "localhost").option(PORT, 5432)
      .option(DATABASE, "vehicle")
      .option(USER, "postgres").option(PASSWORD, "password")
      .build();
    return ConnectionFactories.get(options);
  }

  @Bean
  public ConnectionFactoryInitializer initializer(ConnectionFactory cf) {
    var initializer = new ConnectionFactoryInitializer();
    initializer.setConnectionFactory(cf);
    initializer.setDatabasePopulator(new ResourceDatabasePopulator(
      new ClassPathResource("/sql/vehicle.sql")));
    return initializer;
  }
}
