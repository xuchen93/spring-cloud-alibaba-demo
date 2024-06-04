package com.github.xuchen93.cloud.sentinel;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.math.MathUtil;
import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;

/**
 * @author xuchen.wang
 * @date 2024/6/4
 */
@Slf4j
public class CostCpu {
	public static void main(String[] args) {
		ExecutorService executorService = ThreadUtil.newFixedExecutor(2000, "costCpu", false);
		for (int i = 0; i < 2000; i++) {
			executorService.execute(() -> {
				while (true){
					log.info(DateTime.now().toString());
					MathUtil.arrangementCount(20);
				}
			});
		}
	}
}
