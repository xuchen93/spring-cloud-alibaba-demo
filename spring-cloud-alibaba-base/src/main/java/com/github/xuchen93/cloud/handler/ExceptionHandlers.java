package com.github.xuchen93.cloud.handler;

import com.github.xuchen93.cloud.exception.BusiException;
import com.github.xuchen93.cloud.model.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class ExceptionHandlers {

	@ExceptionHandler(BusiException.class)
	@ResponseBody
	public R throwable(BusiException e) {
		return R.fail(e.getCode(), e.getMessage());
	}

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public R throwable(Exception e) {
		log.error("拦截到未知异常", e);
		return R.fail();
	}
}
