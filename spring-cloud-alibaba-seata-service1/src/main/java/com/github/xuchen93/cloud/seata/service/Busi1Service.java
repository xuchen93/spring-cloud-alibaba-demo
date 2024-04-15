package com.github.xuchen93.cloud.seata.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.github.xuchen93.cloud.model.R;
import com.github.xuchen93.cloud.seata.entity.SeataT1;
import com.github.xuchen93.cloud.seata.feign.Service2Remote;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xuchen.wang
 * @date 2023/12/28
 */
@Service
@Slf4j
public class Busi1Service {

	@Autowired
	Service2Remote service2Remote;

	@Autowired
	SeataT1Service seataT1Service;

	@GlobalTransactional(name = "success_test", rollbackFor = Exception.class)
	public void success() {
		logRqId();
		seataT1Service.save(new SeataT1() {{
			setVarV1(DateUtil.now() + "success");
			setIntV1(1);
		}});
		service2Remote.success();
	}

	@GlobalTransactional(name = "fail1_test", rollbackFor = Exception.class)
	public void fail1() {
		logRqId();
		service2Remote.success();
		seataT1Service.save(new SeataT1() {{
			setVarV1(DateUtil.now() + "fail1");
			setIntV1(1);
		}});
		int i = 1 / 0;
	}

	@GlobalTransactional(name = "fail2_test", rollbackFor = Exception.class)
	public void fail2() {
		logRqId();
		seataT1Service.save(new SeataT1() {{
			setVarV1(DateUtil.now() + "fail2");
			setIntV1(1);
		}});
		R<String> fail = service2Remote.fail();
		log.warn("service2 返回：" + JSONUtil.toJsonStr(fail));
	}

	private void logRqId(){
		String xid = RootContext.getXID();
		log.warn("当前的分布式事务id："+xid);
	}
}
