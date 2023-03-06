package com.github.xuchen93.cloud.nacos;

import com.github.xuchen93.cloud.model.R;
import com.github.xuchen93.cloud.nacos.service.ProviderRemote;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuchen.wang
 * @date 2023/3/1
 */
@Slf4j
@RestController
public class ConsumerController {

	@Autowired
	ProviderRemote providerRemote;

	@GetMapping("/getConsumer")
	public R<String> getConsumer(@RequestParam String key) {
		log.info("consumer接到请求：" + key);
		R<String> r = providerRemote.getProvider(key);
		log.info("consumer请求provider: {}", r);
		return r;
	}
}
