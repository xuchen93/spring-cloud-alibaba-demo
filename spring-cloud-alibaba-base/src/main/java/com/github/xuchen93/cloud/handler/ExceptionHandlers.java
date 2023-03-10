package com.github.xuchen93.cloud.handler;

import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.github.xuchen93.cloud.exception.BusiException;
import com.github.xuchen93.cloud.model.R;
import com.github.xuchen93.cloud.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.Arrays;

@Slf4j
@ControllerAdvice
public class ExceptionHandlers {

	@ExceptionHandler(BusiException.class)
	@ResponseBody
	public R throwable(BusiException e) {
		return R.fail(e.getCode(), e.getMessage());
	}

	@SuppressWarnings("OptionalGetWithoutIsPresent")
	@ExceptionHandler(UndeclaredThrowableException.class)
	@ResponseBody
	public R throwable(UndeclaredThrowableException e) {
		if (e.getUndeclaredThrowable() instanceof FlowException) {
			FlowException flowException = (FlowException) e.getUndeclaredThrowable();
			StackTraceElement traceElement = Arrays.stream(e.getStackTrace())
					.filter(i -> !i.getClassName().equals(RequestLoggerAspect.class.getName()))
					.filter(i -> i.getClassName().startsWith("com.github.xuchen93"))
					.findFirst().get();
			log.info("{}.{}被[{}]限流拦截", CommonUtil.getOriginClassName(traceElement.getClassName()), traceElement.getMethodName(),flowException.getRule().getResource());
			return R.fail("请求太频繁了，过会再试试吧");
		}
		return R.fail();
	}

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public R throwable(Exception e) {
		log.error("拦截到未知异常", e);
		return R.fail();
	}
}
