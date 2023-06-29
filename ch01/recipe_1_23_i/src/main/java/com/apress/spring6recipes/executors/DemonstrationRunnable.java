package com.apress.spring6recipes.executors;

import com.apress.spring6recipes.utils.Utils;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class DemonstrationRunnable implements Runnable {

  @Override
  public void run() {
    Utils.sleep(1, TimeUnit.SECONDS);

    System.out.printf("%s - %s%n", LocalDateTime.now(), Thread.currentThread().getName());
  }
}
