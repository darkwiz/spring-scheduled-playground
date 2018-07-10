package com.example.schedulerdemo;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.ScheduledMethodRunnable;
import org.springframework.stereotype.Component;

@Component
public class CustomTaskScheduler extends ThreadPoolTaskScheduler {
	

	private static final long serialVersionUID = 1L;
	
    private final int POOL_SIZE = 10;
    
    private static final Logger logger = LoggerFactory.getLogger(CustomTaskScheduler.class);


	private final Map<Object, ScheduledFuture<?>> scheduledTasks =
	            new IdentityHashMap<>();
	
	public Map<Object, ScheduledFuture<?>> getScheduledTasks(){
		return this.scheduledTasks;
	}

	
	  @Override
	  public void setThreadNamePrefix(String threadNamePrefix){
		  super.setThreadNamePrefix(threadNamePrefix);
	  }
	
	   @Override
       public ScheduledFuture<?> scheduleAtFixedRate(Runnable task, long period) {
           ScheduledFuture<?> future = super.scheduleAtFixedRate(task, period);

           logger.info("XXXXXXXXXXXXXXXXXXXXX {}", period );
           


           ScheduledMethodRunnable runnable = (ScheduledMethodRunnable) task;
           scheduledTasks.put(runnable.getTarget(), future);

           return future;
       }

       @Override
       public ScheduledFuture<?> scheduleAtFixedRate(Runnable task, Date startTime, long period) {
           ScheduledFuture<?> future = super.scheduleAtFixedRate(task, startTime, period);
           logger.info("yyyyyyyyyyyyyyy" );

           ScheduledMethodRunnable runnable = (ScheduledMethodRunnable) task;
           scheduledTasks.put(runnable.getTarget(), future);

           return future;
       }
       
       @Override
       public ScheduledFuture<?> scheduleWithFixedDelay(Runnable task, Date startTime, long delay) {
    	   ScheduledFuture<?> future = super.scheduleWithFixedDelay(task, startTime, delay);
           logger.info("zzzzzzz");

           return future;
       }
       
       @Override
       public ScheduledFuture<?> scheduleWithFixedDelay(Runnable task, long delay){
    	   ScheduledFuture<?> future = super.scheduleWithFixedDelay(task, delay);
           logger.info("kkkkkkkk");

           return future;
       }
  }