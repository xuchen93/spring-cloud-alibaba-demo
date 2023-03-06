package com.github.xuchen93.cloud.nacos;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import lombok.SneakyThrows;
import org.slf4j.LoggerFactory;

import java.util.Properties;

class NacosMainTests {

	static {
		LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
		loggerContext.getLoggerList().forEach(i -> i.setLevel(Level.INFO));
	}

	@SneakyThrows
	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.put(PropertyKeyConst.SERVER_ADDR, "121.4.58.44:8848");
		ConfigService service = NacosFactory.createConfigService(properties);
		String config = service.getConfig("spring-cloud-alibaba-nacos", "DEFAULT_GROUP", 5000);
		System.out.println(config);

	}
}
