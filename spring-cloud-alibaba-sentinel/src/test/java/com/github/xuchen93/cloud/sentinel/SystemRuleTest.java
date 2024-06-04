package com.github.xuchen93.cloud.sentinel;

import cn.hutool.core.thread.ThreadUtil;
import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.system.SystemRule;
import com.alibaba.csp.sentinel.slots.system.SystemRuleManager;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;

/**
 * @author xuchen.wang
 * @date 2024/6/4
 */
@Slf4j
public class SystemRuleTest {
	public static void main(String[] args) throws Exception {
		SystemRule rule = new SystemRule();
		rule.setHighestCpuUsage(0.4);
		SystemRuleManager.loadRules(Collections.singletonList(rule));
		while (true) {
			Entry entry = null;
			try {
				entry = SphU.entry("methodA", EntryType.IN);
				busiMethod();
			} catch (BlockException e1) {
				log.error("block exception");
				ThreadUtil.sleep(3000);
			} catch (Exception e2) {
				log.error("other exception");
				// biz exception
			} finally {
				if (entry != null) {
					entry.exit();
				}
			}
		}
	}

	private static void busiMethod() {
		log.info("busiMethod start");
		ThreadUtil.sleep(1000);
		log.info("busiMethod end");
	}
}
