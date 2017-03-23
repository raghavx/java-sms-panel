package com.raghavx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

@Configuration
public class ThemeleafConfig {

	@Bean
	public SpringSecurityDialect getSpringSecurityDialect() {
		return new SpringSecurityDialect();
	}

}
