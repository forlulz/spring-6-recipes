package com.apress.spring6recipes.vehicle;

import com.apress.spring6recipes.vehicle.config.VehicleConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import reactor.core.publisher.Flux;

import java.util.concurrent.CountDownLatch;

public class Main {

  public static void main(String[] args) throws Exception {
    var cfg = VehicleConfiguration.class;
    try (var ctx = new AnnotationConfigApplicationContext(cfg)) {
      var vehicleDao = ctx.getBean(VehicleDao.class);
      var vehicle1 = new Vehicle("TEM0442", "Blue", 4, 4);
      var vehicle2 = new Vehicle("TEM0443", "Black", 4, 6);

      var latch = new CountDownLatch(1);
      var vehicles = Flux.just(vehicle1, vehicle2);

      vehicles.flatMap(vehicleDao::save)
        .thenMany(vehicleDao.findAll().doOnNext(System.out::println).flatMap(vehicleDao::delete))
        .doOnTerminate(latch::countDown).subscribe();

      latch.await();
    }
  }
}
