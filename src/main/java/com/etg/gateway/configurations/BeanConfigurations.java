package com.etg.gateway.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfigurations {

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
