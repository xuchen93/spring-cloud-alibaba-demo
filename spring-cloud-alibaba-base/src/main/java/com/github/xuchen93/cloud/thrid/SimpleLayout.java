package com.github.xuchen93.cloud.thrid;

import ch.qos.logback.classic.layout.TTLLLayout;
import ch.qos.logback.classic.pattern.ThrowableProxyConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.util.CachingDateFormatter;
import cn.hutool.core.util.StrUtil;
import com.github.xuchen93.cloud.util.CommonUtil;
import lombok.Data;

/**
 * @author xuchen.wang
 * @date 2023/3/7
 */
public class SimpleLayout extends TTLLLayout {

	CachingDateFormatter cachingDateFormatter = new CachingDateFormatter("HH:mm:ss.SSS");
	ThrowableProxyConverter tpc = new ThrowableProxyConverter();

	private LogConfig logConfig;

	public SimpleLayout() {
		logConfig = new LogConfig();
	}

	public SimpleLayout(LogConfig logConfig) {
		this.logConfig = logConfig;
	}

	@Override
	public void start() {
		tpc.start();
		super.start();
	}

	@Override
	public String doLayout(ILoggingEvent event) {
		if (!isStarted()) {
			return CoreConstants.EMPTY_STRING;
		}
		StringBuilder sb = new StringBuilder();
		if (logConfig.isTime()){
			long timestamp = event.getTimeStamp();
			sb.append(cachingDateFormatter.format(timestamp));
			sb.append(" | ");
		}
		if (logConfig.isThread()){
			sb.append(StrUtil.fillAfter(event.getThreadName(), ' ', 16));
			sb.append(" | ");
		}
		if (logConfig.isMethod()){
			String[] split = CommonUtil.getCurrentClassName(14).split("\\.");
			String classAndMethod = split[split.length - 1] + "." + CommonUtil.getOriginMethodName(CommonUtil.getCurrentMethodName(14));
			sb.append(StrUtil.fillAfter(classAndMethod, ' ', (classAndMethod.length() / 5 + 1) * 5));
			sb.append(" | ");
		}
		sb.append(event.getFormattedMessage());
		sb.append(CoreConstants.LINE_SEPARATOR);
		IThrowableProxy tp = event.getThrowableProxy();
		if (tp != null) {
			String stackTrace = tpc.convert(event);
			sb.append(stackTrace);
		}
		return sb.toString();
	}
}
