package com.github.xuchen93.cloud.nacos.controller;

import com.github.xuchen93.cloud.model.R;
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
@RequestMapping("/config")
public class ConfigController {

	@Value("${xuchen.config.key1}")
	String key1;

	@Value("${xuchen.config.key2}")
	String key2;

	@GetMapping("/key")
	public R<String> queryKey() {
		log.info(key1);
		return R.success(key1);
	}

	@GetMapping("/key2")
	public R<String> queryKe2y() {
		log.info(key2);
		return R.success(key2);
	}
}
