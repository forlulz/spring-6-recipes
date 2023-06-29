package com.apress.spring6recipes.vehicle.config;

import com.apress.spring6recipes.vehicle.PlainJdbcVehicleDao;
import com.apress.spring6recipes.vehicle.VehicleDao;
import org.postgresql.Driver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

@Configuration
public class VehicleConfiguration {

  @Bean
  public VehicleDao vehicleDao(DataSource dataSource) {
    return new PlainJdbcVehicleDao(dataSource);
  }

  @Bean
  public DataSource dataSource() {
    var dataSource = new SimpleDriverDataSource();
    dataSource.setDriverClass(Driver.class);
    dataSource.setUrl("jdbc:postgresql://localhost:5432/vehicle");
    dataSource.setUsername("postgres");
    dataSource.setPassword("password");
    return dataSource;
  }
}
