package com.etg.gateway.configurations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import com.etg.gateway.common.Constants;

@EnableScheduling
@Configuration
public class SchedulingConfiguration implements SchedulingConfigurer {

	private final Logger logger = LoggerFactory.getLogger(SchedulingConfiguration.class);
	private final ThreadPoolTaskScheduler taskScheduler;

	public SchedulingConfiguration() {
		taskScheduler = new ThreadPoolTaskScheduler();
		taskScheduler.setErrorHandler(e -> logger.error(Constants.SCHEDULED_ERROR_MESSAGE, e));
		taskScheduler.setThreadNamePrefix("@scheduled-");

		taskScheduler.initialize();
	}

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setScheduler(taskScheduler);
	}
}
