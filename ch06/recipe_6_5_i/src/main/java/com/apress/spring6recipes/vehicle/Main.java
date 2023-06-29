package com.apress.spring6recipes.vehicle;

import com.apress.spring6recipes.vehicle.config.VehicleConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

  public static void main(String[] args) throws Exception {

    var cfg = VehicleConfiguration.class;
    try (var context = new AnnotationConfigApplicationContext(cfg)) {
      var vehicleDao = context.getBean(VehicleDao.class);
      var vehicle = new Vehicle("EX0001", "Green", 4, 4);
      vehicleDao.insert(vehicle);
    }
  }
}
