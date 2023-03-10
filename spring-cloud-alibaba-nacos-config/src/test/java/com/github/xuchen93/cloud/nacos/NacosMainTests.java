package com.github.xuchen93.cloud.nacos;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.layout.TTLLLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.LoggerContextVO;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.encoder.Encoder;
import ch.qos.logback.core.encoder.LayoutWrappingEncoder;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.github.xuchen93.cloud.thrid.SimpleLayout;
import lombok.SneakyThrows;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

import java.util.Map;
import java.util.Properties;

class NacosMainTests {

	@SneakyThrows
	public static void main(String[] args) {
//		Properties properties = new Properties();
//		properties.put(PropertyKeyConst.SERVER_ADDR, "121.4.58.44:8848");
//		ConfigService service = NacosFactory.createConfigService(properties);
//		String config = service.getConfig("spring-cloud-alibaba-nacos", "DEFAULT_GROUP", 5000);
//		System.out.println(config);

	}
}
