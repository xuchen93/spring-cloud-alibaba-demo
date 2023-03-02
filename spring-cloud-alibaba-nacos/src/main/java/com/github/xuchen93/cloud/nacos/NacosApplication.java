package com.github.xuchen93.cloud.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class NacosApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(NacosApplication.class, args);
		System.out.println(applicationContext.getEnvironment().getProperty("xuchen.config.key1"));
	}

}
