package com.apress.spring6recipes.nosql.config;

import com.apress.spring6recipes.nosql.RedisVehicleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

import static redis.clients.jedis.Protocol.DEFAULT_PORT;

@Configuration
public class RedisConfig {

  @Bean
  public Jedis jedis() {
    return new Jedis("localhost", DEFAULT_PORT);
  }

  @Bean
  public RedisVehicleRepository vehicleRepository(Jedis jedis) {
    return new RedisVehicleRepository(jedis);
  }
}
