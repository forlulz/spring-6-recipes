package com.apress.spring6recipes.vehicle.config;

import com.apress.spring6recipes.vehicle.NamedJdbcVehicleDao;
import com.apress.spring6recipes.vehicle.VehicleDao;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class VehicleConfiguration {

  @Bean
  public VehicleDao vehicleDao(JdbcTemplate jdbcTemplate) {
    NamedJdbcVehicleDao vehicleDao = new NamedJdbcVehicleDao();
    vehicleDao.setJdbcTemplate(jdbcTemplate);
    return vehicleDao;
  }

  @Bean
  public JdbcTemplate jdbcTemplate(DataSource dataSource) {
    return new JdbcTemplate(dataSource);
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
