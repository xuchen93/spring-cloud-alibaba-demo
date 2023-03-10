package com.github.xuchen93.cloud.sentinel.controller;

import cn.hutool.core.thread.ThreadUtil;
import com.github.xuchen93.cloud.thrid.LogConfig;
import com.github.xuchen93.cloud.util.CommonUtil;
import com.github.xuchen93.cloud.util.HttpPackUtil;
import lombok.SneakyThrows;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author xuchen.wang
 * @date 2023/3/9
 */
public class BaseControllerTest {

	protected static ThreadPoolExecutor service = null;

	static {
		HttpPackUtil.setPort(9180);
		HttpPackUtil.logEnable = false;
		CommonUtil.formatSimpleLog(new LogConfig(){{
			setTime(false);
			setThread(false);
			setMethod(false);
		}});
	}

	@SneakyThrows
	protected static void doBefore(int threadCount) {
		service = (ThreadPoolExecutor) Executors.newFixedThreadPool(threadCount);
		service.prestartCoreThread();
		CountDownLatch countDownLatch = new CountDownLatch(threadCount);
		for (int i = 0; i < threadCount; i++) {
			service.execute(() -> {
				ThreadUtil.sleep(30);
				countDownLatch.countDown();
			});
		}
		countDownLatch.await();
	}

	protected static void doAfter() {
		service.shutdown();
	}
}
