package com.apress.spring6recipes.executors;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.core.task.support.TaskExecutorAdapter;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.scheduling.concurrent.ScheduledExecutorFactoryBean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledExecutorService;

@Component
public class SpringExecutorsDemo {

  @Autowired
  private TaskExecutorAdapter taskExecutorAdapter;

  @Autowired
  private SimpleAsyncTaskExecutor asyncTaskExecutor;

  @Autowired
  private SyncTaskExecutor syncTaskExecutor;

  @Autowired
  private ThreadPoolTaskExecutor threadPoolTaskExecutor;

  @Autowired
  private ConcurrentTaskExecutor virtualThreadsTaskExecutor;

  @Autowired
  private DemonstrationRunnable task;

  public static void main(String[] args) {
    var cfg = ExecutorsConfiguration.class;
    try (var ctx = new AnnotationConfigApplicationContext(cfg)) {
    }
  }

  @PostConstruct
  public void submitJobs() {
    taskExecutorAdapter.submit(task);
    asyncTaskExecutor.submit(task);
    syncTaskExecutor.execute(task);

    for (int i = 0; i < 500; i++)
      virtualThreadsTaskExecutor.submit(task);

    for (int i = 0; i < 500; i++)
      threadPoolTaskExecutor.submit(task);
  }
}
