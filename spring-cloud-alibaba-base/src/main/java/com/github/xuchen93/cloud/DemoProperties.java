package com.github.xuchen93.cloud;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author xuchen.wang
 * @date 2023/3/9
 */
@Data
@Component
@ConfigurationProperties("demo")
public class DemoProperties {
	private Log log = new Log();

	@Data
	public static class Log {
		/**
		 * 是否控制台输入日志
		 */
		private boolean enableStdout = true;
	}
}
