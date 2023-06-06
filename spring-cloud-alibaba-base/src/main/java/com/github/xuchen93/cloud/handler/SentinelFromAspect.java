package com.github.xuchen93.cloud.handler;

import cn.hutool.core.util.StrUtil;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.context.ContextUtil;
import com.github.xuchen93.cloud.annotation.SentinelFrom;
import com.github.xuchen93.cloud.exception.BusiException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
@Slf4j
public class SentinelFromAspect {

	public SentinelFromAspect() {
		log.info("注入【sentinel from】拦截");
	}

	@Pointcut("@annotation(com.github.xuchen93.cloud.annotation.SentinelFrom) && @annotation(com.alibaba.csp.sentinel.annotation.SentinelResource)")
	public void sentinelFromPointCut() {

	}

	@SneakyThrows
	@Before("sentinelFromPointCut()")
	public void before(JoinPoint joinPoint) {
		Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
		SentinelFrom sentinelFrom = method.getAnnotation(SentinelFrom.class);
		if (sentinelFrom == null){
			sentinelFrom = method.getClass().getAnnotation(SentinelFrom.class);
		}
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		String header = request.getHeader(sentinelFrom.value());
		if (StrUtil.isBlank(header)) {
			throw new BusiException("缺少请求头");
		}
		SentinelResource sentinelResource = method.getAnnotation(SentinelResource.class);
		log.info("资源[{}]记录来源[{}]", sentinelResource.value(), header);
		ContextUtil.enter(sentinelResource.value(), header);
		SphU.entry(sentinelResource.value());
	}

}

