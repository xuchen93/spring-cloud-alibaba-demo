package com.github.xuchen93.cloud.sentinel;

import cn.hutool.http.HttpResponse;
import com.github.xuchen93.cloud.util.HttpPackUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xuchen.wang
 * @date 2023/3/3
 */
public class SentinelHttpTest {
	public static void main(String[] args) {
		HttpPackUtil.setPort(9180);

		ExecutorService service = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 1000; i++) {
			service.execute(()->{
				HttpResponse response = HttpPackUtil.createGet("hello").execute();
				System.out.println(response.body());
			});
		}
		service.shutdown();
	}

}
