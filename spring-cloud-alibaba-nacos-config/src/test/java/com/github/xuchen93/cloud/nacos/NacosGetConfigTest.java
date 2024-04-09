package com.github.xuchen93.cloud.nacos;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.client.config.NacosConfigService;
import lombok.SneakyThrows;

import java.util.Properties;

class NacosGetConfigTest {

	@SneakyThrows
	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.put(PropertyKeyConst.SERVER_ADDR, "124.220.50.39:8848");
		properties.put(PropertyKeyConst.NAMESPACE, "728d1960-9196-4d4f-87c5-24cc910081c6");
		NacosConfigService service = (NacosConfigService) NacosFactory.createConfigService(properties);
//		System.out.println(service.getServerStatus());
//		service.publishConfig("nacos-test", "DEFAULT_GROUP", "test");
		String config = service.getConfig("nacos-config", "DEFAULT_GROUP", 5000);
//		String config = service.getConfig("ruoyi-gateway-dev.yml", "DEFAULT_GROUP", 5000);
		System.out.println(config);

	}
}
