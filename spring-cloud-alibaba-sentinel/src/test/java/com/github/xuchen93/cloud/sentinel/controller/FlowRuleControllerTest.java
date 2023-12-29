package com.github.xuchen93.cloud.sentinel.controller;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.http.HttpResponse;
import com.github.xuchen93.cloud.util.CommonUtil;
import com.github.xuchen93.cloud.util.HttpPackUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xuchen.wang
 * @date 2023/3/3
 */
@Slf4j
public class FlowRuleControllerTest extends BaseControllerTest {


	public static void main(String[] args) {
		int count = 1;
		doBefore(count);
		HttpPackUtil.headerMap.put("origin","testApp1");
		for (int i = 0; i < 1000; i++) {
			qpsRefuse(count);
//			qpsWarmUp(count);
//			qpsWarmUpRateLimit(count);
//			qpsRateLimit(count);
//			qpsByApp(count);
//			ThreadUtil.sleep(1000);
			System.out.println("==============");
		}
		doAfter();
	}


	public static void qpsRefuse(int count) {
		String methodName = CommonUtil.getCurrentMethodName(2);
		for (int i = 1; i <= count; i++) {
			int finalI = i;
			service.execute(() -> {
				HttpResponse response = HttpPackUtil.createGet(methodName + "-" + finalI, methodName).execute();
				log.info("方法【{}】第【{}】次执行请求，返回：{}", methodName, finalI, response.body());
			});
		}
	}

	public static void qpsWarmUp(int count) {
		String methodName = CommonUtil.getCurrentMethodName(2);
		for (int i = 1; i <= count; i++) {
			int finalI = i;
			service.execute(() -> {
				HttpResponse response = HttpPackUtil.createGet(methodName + "-" + finalI, methodName).execute();
				log.info("方法【{}】第【{}】次执行请求，返回：{}", methodName, finalI, response.body());
			});
		}
	}

	public static void qpsWarmUpRateLimit(int count) {
		String methodName = CommonUtil.getCurrentMethodName(2);
		for (int i = 1; i <= count; i++) {
			int finalI = i;
			service.execute(() -> {
				HttpResponse response = HttpPackUtil.createGet(methodName + "-" + finalI, methodName).execute();
				log.info("方法【{}】第【{}】次执行请求，返回：{}", methodName, finalI, response.body());
			});
		}
	}

	public static void qpsRateLimit(int count) {
		String methodName = CommonUtil.getCurrentMethodName(2);
		for (int i = 1; i <= count; i++) {
			int finalI = i;
			service.execute(() -> {
				HttpResponse response = HttpPackUtil.createGet(methodName + "-" + finalI, methodName).execute();
				log.info("方法【{}】第【{}】次执行请求，返回：{}", methodName, finalI, response.body());
			});
		}
	}

	public static void qpsByApp(int count) {

		String methodName = CommonUtil.getCurrentMethodName(2);
		for (int i = 1; i <= count; i++) {
			int finalI = i;
			service.execute(() -> {
				HttpResponse response = HttpPackUtil.createGet(methodName + "-" + finalI, methodName).execute();
				log.info("方法【{}】第【{}】次执行请求，返回：{}", methodName, finalI, response.body());
			});
		}
	}

}
