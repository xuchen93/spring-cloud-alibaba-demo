package com.github.xuchen93.cloud.nacos.controller;

import com.github.xuchen93.cloud.model.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuchen.wang
 * @date 2023/3/1
 */
@Slf4j
@RestController
public class ProviderController {

	@GetMapping("/getProvider")
	public R<String> getProvider(@RequestParam String key) {
		log.info("provider接到请求："+key);
		return R.success("get from provider "+key);
	}
}
