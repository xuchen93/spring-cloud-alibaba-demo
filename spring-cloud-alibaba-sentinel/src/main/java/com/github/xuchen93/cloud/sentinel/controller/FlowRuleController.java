package com.github.xuchen93.cloud.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.github.xuchen93.cloud.model.R;
import com.github.xuchen93.cloud.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xuchen.wang
 * @date 2023/3/1
 */
@Slf4j
@RestController
public class FlowRuleController {

	private static final Map<String, AtomicInteger> counterMap = new ConcurrentHashMap<>();

	@GetMapping("/qpsRefuse")
	@SentinelResource("qpsRefuse")
	public R<String> qpsRefuse() {
		String methodName = CommonUtil.getCurrentMethodName(2);
		AtomicInteger atomicInteger = counterMap.computeIfAbsent(methodName, s -> new AtomicInteger());
		int count = atomicInteger.incrementAndGet();
		log.info("【{}】第【{}】次接到请求", methodName, count);
		return R.success("" + count);
	}

	@GetMapping("/qpsWarmUp")
	@SentinelResource("qpsWarmUp")
	public R<String> qpsWarmUp() {
		String methodName = CommonUtil.getCurrentMethodName(2);
		AtomicInteger atomicInteger = counterMap.computeIfAbsent(methodName, s -> new AtomicInteger());
		int count = atomicInteger.incrementAndGet();
		log.info("【{}】第【{}】次接到请求", methodName, count);
		return R.success("" + count);
	}

	@GetMapping("/qpsWarmUpRateLimit")
	@SentinelResource("qpsWarmUpRateLimit")
	public R<String> qpsWarmUpRateLimit() {
		String methodName = CommonUtil.getCurrentMethodName(2);
		AtomicInteger atomicInteger = counterMap.computeIfAbsent(methodName, s -> new AtomicInteger());
		int count = atomicInteger.incrementAndGet();
		log.info("【{}】第【{}】次接到请求", methodName, count);
		return R.success("" + count);
	}

	@GetMapping("/qpsRateLimit")
	@SentinelResource("qpsRateLimit")
	public R<String> qpsRateLimit() {
		String methodName = CommonUtil.getCurrentMethodName(2);
		AtomicInteger atomicInteger = counterMap.computeIfAbsent(methodName, s -> new AtomicInteger());
		int count = atomicInteger.incrementAndGet();
		log.info("【{}】第【{}】次接到请求", methodName, count);
		return R.success("" + count);
	}
}
