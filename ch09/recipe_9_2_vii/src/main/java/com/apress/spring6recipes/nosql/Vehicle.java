package com.apress.spring6recipes.nosql;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.UUID;

@RedisHash("vehicles")
public record Vehicle(String id, @Indexed String vehicleNo, String color, int wheel, int seat) {

  public Vehicle(String vehicleNo, String color, int wheel, int seat) {
    this(UUID.randomUUID().toString(), vehicleNo, color, wheel, seat);
  }
}
