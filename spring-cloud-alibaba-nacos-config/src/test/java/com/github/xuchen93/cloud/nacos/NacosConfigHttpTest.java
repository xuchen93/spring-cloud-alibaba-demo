package com.github.xuchen93.cloud.nacos;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.github.xuchen93.cloud.util.HttpPackUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xuchen.wang
 * @date 2024/4/3
 */
@Slf4j
public class NacosConfigHttpTest {
	public static void main(String[] args) {
		HttpPackUtil.setPort(8081);
		HttpResponse response = HttpPackUtil.createGet("config/key").execute();
		log.info(JSONUtil.toJsonPrettyStr(response.body()));
	}
}
