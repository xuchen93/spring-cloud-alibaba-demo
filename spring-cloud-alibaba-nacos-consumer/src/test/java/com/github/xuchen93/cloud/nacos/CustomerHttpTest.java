package com.github.xuchen93.cloud.nacos;

import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.github.xuchen93.cloud.util.HttpPackUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xuchen.wang
 * @date 2024/4/9
 */
@Slf4j
public class CustomerHttpTest {

	public static void main(String[] args) {
		HttpPackUtil.setPort(9080);
		HttpResponse response = HttpPackUtil.createGet("/getConsumer")
				.body("key=customerTest")
				.execute();
		log.info(JSONUtil.toJsonPrettyStr(response.body()));
	}
}
