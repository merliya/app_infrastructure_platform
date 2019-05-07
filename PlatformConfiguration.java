package com.jbhunt.infrastructure.platform.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jbhunt.biz.securepid.FusePIDReader;
import com.jbhunt.biz.securepid.PIDCredentials;
import com.jbhunt.infrastructure.platform.properties.PlatformApplicationProperties;
import org.springframework.web.client.RestTemplate;

@Configuration
public class PlatformConfiguration {


    @Bean
 	public PIDCredentials pidCredentials() {
    	FusePIDReader fusePIDReader = new FusePIDReader("Infrastructure_platform");
		return fusePIDReader.readPIDCredentials("platformInfra");
 	}

	@Bean
	public PlatformApplicationProperties platformApplicationProperties() {
		return new PlatformApplicationProperties();
	}
}
