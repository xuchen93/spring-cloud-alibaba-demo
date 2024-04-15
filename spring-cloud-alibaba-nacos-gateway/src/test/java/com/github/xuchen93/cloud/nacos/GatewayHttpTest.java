package com.github.xuchen93.cloud.nacos;

import cn.hutool.json.JSONUtil;
import com.github.xuchen93.cloud.util.HttpPackUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xuchen.wang
 * @date 2024/4/9
 */
@Slf4j
public class GatewayHttpTest {
	public static void main(String[] args) {
		HttpPackUtil.setPort(8888);
//		log.info(JSONUtil.toJsonPrettyStr(
//				HttpPackUtil.createGet("nacos-consumer/getConsumer")
//						.body("key=testGatewayConsumer")
//						.execute().body())
//		);
		log.info(JSONUtil.toJsonPrettyStr(
				HttpPackUtil.createGet("nacos-provider/getProvider")
						.body("key=testGatewayProvider")
						.header("Authorization","testAuthorization")
						.execute().body())
		);
	}
}
