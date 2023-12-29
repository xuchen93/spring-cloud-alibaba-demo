package com.github.xuchen93.cloud.sentinel;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.github.xuchen93.cloud.util.NacosUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @author xuchen.wang
 * @date 2023/3/7
 */
public class GenerateSentinelConfig {

	public static void main(String[] args) {
		NacosUtil.initConfig("124.220.50.39:8848");
		pushFlowRule();
//		pushDegradeRule();

		NacosUtil.closeConfig();
	}


	public static void pushFlowRule() {
		List<FlowRule> list = CollUtil.newArrayList(
				new FlowRule(){{
					setResource("qpsRefuse");
					setGrade(RuleConstant.FLOW_GRADE_QPS);
					setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_DEFAULT);
					setCount(10);
				}}
				,new FlowRule(){{
					setResource("qpsWarmUp");
					setGrade(RuleConstant.FLOW_GRADE_QPS);
					setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_WARM_UP);
					setCount(100);
					setWarmUpPeriodSec(10);
				}}
				,new FlowRule(){{
					setResource("qpsWarmUpRateLimit");
					setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_WARM_UP_RATE_LIMITER);
					setCount(10);
					setWarmUpPeriodSec(10);
					setMaxQueueingTimeMs(2000);
				}}
				,new FlowRule(){{
					setResource("qpsRateLimit");
					setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_RATE_LIMITER);
					setCount(10);
					setMaxQueueingTimeMs(200000);
				}}
				,new FlowRule(){{
					setResource("qpsByApp");
					setGrade(RuleConstant.FLOW_GRADE_QPS);
					setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_DEFAULT);
					setCount(10);
					setLimitApp("testApp1");
				}}
				,new FlowRule(){{
					setResource("qpsByApp");
					setGrade(RuleConstant.FLOW_GRADE_QPS);
					setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_DEFAULT);
					setCount(100);
					setLimitApp("testApp2");
				}}
		);
		NacosUtil.push("spring-cloud-alibaba-sentinel-flow", JSONUtil.toJsonPrettyStr(list));
		ThreadUtil.sleep(200);
		System.out.println(NacosUtil.config("spring-cloud-alibaba-sentinel-flow"));
	}


	public static void pushDegradeRule() {
		List<DegradeRule> list = CollUtil.newArrayList(
				new DegradeRule(){{
					setCount(50);
					setTimeWindow(10);
				}}
		);
		NacosUtil.push("spring-cloud-alibaba-sentinel-degrade", JSONUtil.toJsonPrettyStr(list));
		ThreadUtil.sleep(200);
		System.out.println(NacosUtil.config("spring-cloud-alibaba-sentinel-degrade"));
	}
}
