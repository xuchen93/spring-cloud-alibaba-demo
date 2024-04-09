package com.github.xuchen93.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class NacosProvider2Application {

	public static void main(String[] args) {
		SpringApplication.run(NacosProvider2Application.class, args);
	}

}
