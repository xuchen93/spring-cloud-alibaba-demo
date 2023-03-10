package com.github.xuchen93.cloud.util;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.encoder.LayoutWrappingEncoder;
import cn.hutool.core.util.StrUtil;
import com.github.xuchen93.cloud.thrid.LogConfig;
import com.github.xuchen93.cloud.thrid.SimpleLayout;
import org.slf4j.LoggerFactory;

/**
 * @author xuchen.wang
 * @date 2023/3/7
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class CommonUtil {

	public static String getCurrentMethodName(int level){
		return Thread.currentThread().getStackTrace()[level].getMethodName();
	}

	public static String getCurrentClassName(int level){
		return Thread.currentThread().getStackTrace()[level].getClassName();
	}

	public static String getOriginClassName(String className){
		return StrUtil.split(className,"$$EnhancerBySpringCGLIB").get(0);
	}

	public static String getOriginMethodName(String methodName){
		String name = methodName.replace("lambda$", "");
		if (name.charAt(name.length()-2) == '$' ||name.charAt(name.length()-3) == '$'){
			int index = StrUtil.indexOf(name, '$', name.length() - 3);
			name = StrUtil.subPre(name,index);
		}
		return name;
	}

	public static void formatSimpleLog(LogConfig logConfig){
		LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
		loggerContext.getLoggerList().forEach(i -> {
			i.setLevel(Level.INFO);
			i.iteratorForAppenders().forEachRemaining(j -> {
				ConsoleAppender consoleAppender = (ConsoleAppender) j;
				LayoutWrappingEncoder encoder = (LayoutWrappingEncoder) consoleAppender.getEncoder();
				SimpleLayout simpleLayout = new SimpleLayout(logConfig);
				simpleLayout.setContext(loggerContext);
				simpleLayout.start();
				encoder.setLayout(simpleLayout);
			});
		});
	}
}
