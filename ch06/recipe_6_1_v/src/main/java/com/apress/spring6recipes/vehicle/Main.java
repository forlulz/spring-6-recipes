package com.apress.spring6recipes.vehicle;

import com.apress.spring6recipes.vehicle.config.VehicleConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {

  public static void main(String[] args) throws Exception {

    var cfg = VehicleConfiguration.class;
    try (var context = new AnnotationConfigApplicationContext(cfg)) {

      var vehicleDao = context.getBean(VehicleDao.class);
      var vehicle1 = new Vehicle("TEM0022", "Blue", 4, 4);
      var vehicle2 = new Vehicle("TEM0023", "Black", 4, 6);
      var vehicle3 = new Vehicle("TEM0024", "Green", 4, 5);
      vehicleDao.insert(List.of(vehicle1, vehicle2, vehicle3));

      vehicleDao.findAll().forEach(System.out::println);
    }
  }
}
