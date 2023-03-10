package com.github.xuchen93.cloud.sentinel.controller;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;
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
public class DegradeRuleController {

	private static final Map<String, AtomicInteger> counterMap = new ConcurrentHashMap<>();

	@GetMapping("/cost10ms")
	@SentinelResource("cost10ms")
	public R<String> cost10ms() {
		ThreadUtil.sleep(10);
		String methodName = CommonUtil.getCurrentMethodName(2);
		AtomicInteger atomicInteger = counterMap.computeIfAbsent(methodName, s -> new AtomicInteger());
		int count = atomicInteger.incrementAndGet();
		log.info("【{}】第【{}】次接到请求", methodName, count);
		return R.success("" + count);
	}

	@GetMapping("/cost100ms")
	@SentinelResource("cost100ms")
	public R<String> cost100ms() {
		ThreadUtil.sleep(100);
		String methodName = CommonUtil.getCurrentMethodName(2);
		AtomicInteger atomicInteger = counterMap.computeIfAbsent(methodName, s -> new AtomicInteger());
		int count = atomicInteger.incrementAndGet();
		log.info("【{}】第【{}】次接到请求", methodName, count);
		return R.success("" + count);
	}

	@GetMapping("/cost1000ms")
	@SentinelResource("cost1000ms")
	public R<String> cost1000ms() {
		ThreadUtil.sleep(1000);
		String methodName = CommonUtil.getCurrentMethodName(2);
		AtomicInteger atomicInteger = counterMap.computeIfAbsent(methodName, s -> new AtomicInteger());
		int count = atomicInteger.incrementAndGet();
		log.info("【{}】第【{}】次接到请求", methodName, count);
		return R.success("" + count);
	}

	@GetMapping("/exception10per")
	@SentinelResource("exception10per")
	public R<String> exception10per() {
		String methodName = CommonUtil.getCurrentMethodName(2);
		AtomicInteger atomicInteger = counterMap.computeIfAbsent(methodName, s -> new AtomicInteger());
		int count = atomicInteger.incrementAndGet();
		boolean except = RandomUtil.randomInt(10) < 1;
		log.info("【{}】第【{}】次接到请求，异常：{}", methodName, count, except);
		if (except) {
			throw new RuntimeException("10per exception");
		}
		return R.success("" + count);
	}

	@GetMapping("/exception50per")
	@SentinelResource("exception50per")
	public R<String> exception50per() {
		String methodName = CommonUtil.getCurrentMethodName(2);
		AtomicInteger atomicInteger = counterMap.computeIfAbsent(methodName, s -> new AtomicInteger());
		int count = atomicInteger.incrementAndGet();
		boolean except = RandomUtil.randomInt(10) < 5;
		log.info("【{}】第【{}】次接到请求，异常：{}", methodName, count, except);
		if (except) {
			throw new RuntimeException("50per exception");
		}
		return R.success("" + count);
	}

	@GetMapping("/exceptionCount")
	@SentinelResource("exceptionCount")
	public R<String> exceptionCount() {
		String methodName = CommonUtil.getCurrentMethodName(2);
		AtomicInteger atomicInteger = counterMap.computeIfAbsent(methodName, s -> new AtomicInteger());
		int count = atomicInteger.incrementAndGet();
		log.info("【{}】第【{}】次接到请求", methodName, count);
		throw new RuntimeException("exception count = " + count);
	}
}
