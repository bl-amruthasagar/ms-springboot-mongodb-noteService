package com.ams.noteapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class NoteAppConfig {

	
	@Bean
	public RestTemplate getRestTemplate() {
	      return new RestTemplate();
	   }
}
