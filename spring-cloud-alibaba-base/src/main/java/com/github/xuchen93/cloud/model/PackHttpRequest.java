package com.github.xuchen93.cloud.model;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.Method;
import cn.hutool.json.JSONUtil;
import com.github.xuchen93.cloud.util.HttpPackUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PackHttpRequest extends HttpRequest {

	private String id;

	public PackHttpRequest(String url) {
		this(null, url);
	}

	public PackHttpRequest(String id, String url) {
		super(url);
		this.id = id;
	}


	@Override
	public HttpResponse execute() {
		if (HttpPackUtil.logEnable) {
			log.info(toString());
		}
		long timeMillis = System.currentTimeMillis();
		HttpResponse response = super.execute();
		if (HttpPackUtil.costEnable) {
			log.info("请求id[" + id + "] 耗时(ms)：" + (System.currentTimeMillis() - timeMillis));
		}
		return response;
	}

	@Override
	public HttpResponse executeAsync() {
		if (HttpPackUtil.logEnable) {
			log.info(toString());
		}
		return super.execute(true);
	}

	@Override
	public HttpRequest method(Method method) {
		super.method(method);
		return this;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (id != null) {
			sb.append("请求id：").append(id).append(StrUtil.CRLF);
		}
		sb.append("请求类型：" + getMethod().name()).append(StrUtil.CRLF);
		if (super.form() != null) {
			sb.append("请求表单：").append(StrUtil.CRLF).append(JSONUtil.toJsonPrettyStr(form())).append(StrUtil.CRLF);
		}
		sb.append(super.toString());
		return sb.toString();
	}
}
