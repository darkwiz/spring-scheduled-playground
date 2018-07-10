package com.example.schedulerdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import com.example.schedulerdemo.CustomTaskScheduler;

/**
 * Created by rajeevkumarsingh on 02/08/17.
 */
@Configuration
public class SchedulerConfig implements SchedulingConfigurer {
    
    @Autowired
    private TaskScheduler customTaskScheduler;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
//      threadPoolTaskScheduler.setPoolSize(POOL_SIZE);
    	((CustomTaskScheduler) customTaskScheduler).setThreadNamePrefix("my-scheduled-task-pool-");
//      threadPoolTaskScheduler.initialize();
        scheduledTaskRegistrar.setTaskScheduler(customTaskScheduler);
    }
}
