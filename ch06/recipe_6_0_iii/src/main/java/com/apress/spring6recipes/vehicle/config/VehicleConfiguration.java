package com.apress.spring6recipes.vehicle.config;

import com.apress.spring6recipes.vehicle.PlainJdbcVehicleDao;
import com.apress.spring6recipes.vehicle.VehicleDao;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class VehicleConfiguration {

  @Bean
  public VehicleDao vehicleDao(DataSource dataSource) {
    return new PlainJdbcVehicleDao(dataSource);
  }

  @Bean
  public DataSource dataSource() {

    HikariDataSource dataSource = new HikariDataSource();
    dataSource.setUsername("postgres");
    dataSource.setPassword("password");
    dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/vehicle");
    dataSource.setMinimumIdle(2);
    dataSource.setMaximumPoolSize(5);
    return dataSource;
  }

}
