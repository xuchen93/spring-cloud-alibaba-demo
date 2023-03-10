package com.github.xuchen93.cloud.sentinel.controller;

import cn.hutool.http.HttpResponse;
import com.github.xuchen93.cloud.util.CommonUtil;
import com.github.xuchen93.cloud.util.HttpPackUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xuchen.wang
 * @date 2023/3/3
 */
@Slf4j
public class DegradeRuleControllerTest extends BaseControllerTest {


	public static void main(String[] args) {
		doBefore(10);
//		degradeRule1(15);
//		degradeRule2(15);

		doAfter();
	}

	public static void degradeRule1(int count) {
		String methodName = CommonUtil.getCurrentMethodName(2);
		for (int i = 1; i <= count; i++) {
			int finalI = i;
			service.execute(() -> {
				HttpResponse response = HttpPackUtil.createGet(methodName).execute();
				log.info("方法【{}】第【{}】次执行请求，返回：{}", methodName, finalI, response.body());
			});
		}
	}

	public static void degradeRule2(int count) {
		String methodName = CommonUtil.getCurrentMethodName(2);
		for (int i = 1; i <= count; i++) {
			int finalI = i;
			service.execute(() -> {
				HttpResponse response = HttpPackUtil.createGet(methodName).execute();
				log.info("方法【{}】第【{}】次执行请求，返回：{}", methodName, finalI, response.body());
			});
		}
	}

}
