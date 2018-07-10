package com.example.schedulerdemo;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SchedulerDemoApplicationTests {
	
	@Autowired
	private CustomTaskScheduler customTaskScheduler; 
	
    private static final Logger logger = LoggerFactory.getLogger(SchedulerDemoApplicationTests.class);


	@Test
	public void contextLoads() {
		Map<Object, ScheduledFuture<?>> sTasks = customTaskScheduler.getScheduledTasks();
		for (Entry<Object, ScheduledFuture<?>> task : sTasks.entrySet()) {
			ScheduledFuture<?> sc = task.getValue();
			
			logger.info("Taskkkkk is cancelled {}", sc.isCancelled());
			logger.info("\n Taskkkkk cancel {}", sc.cancel(true));
			logger.info("\n Taskkkkk is cancelled {}", sc.isCancelled());
		   
		};
	}

}
