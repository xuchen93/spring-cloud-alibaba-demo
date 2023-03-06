package com.github.xuchen93.cloud.sentinel;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.github.xuchen93.cloud.model.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @author xuchen.wang
 * @date 2023/3/1
 */
@Slf4j
@RestController
public class SentinelController {

	@GetMapping("/hello")
	@SentinelResource("hello")
	public R<String> hello() {
		log.info("SentinelController接到请求");
		return R.success(LocalDateTime.now().toString());
	}
}
