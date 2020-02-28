package com.aimdek.beanscope;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BeanConfig {
	
	@Bean
	@Scope(value="singleton")
	public MyBeans myBeans() {
		return new MyBeans();
	}
	
	@Bean
	@Scope(value="prototype")
	public MyBeans2 myBeans2() {
		return new MyBeans2();
	}
}
