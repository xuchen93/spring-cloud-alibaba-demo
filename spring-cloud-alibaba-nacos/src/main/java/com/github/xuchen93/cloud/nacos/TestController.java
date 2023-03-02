package com.github.xuchen93.cloud.nacos;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.github.xuchen93.cloud.model.base.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuchen.wang
 * @date 2023/3/1
 */
@Slf4j
@RefreshScope
@RestController
@RequestMapping("/test")
public class TestController {

	@Value("${xuchen.config.key1:}")
	String key1;

	@GetMapping("/key")
	public Result<String> queryKey() {
		log.info(key1);
		return Result.success(key1);
	}
}
