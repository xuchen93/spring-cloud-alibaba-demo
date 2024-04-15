package com.github.xuchen93.cloud.seata.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.thread.ThreadUtil;
import com.github.xuchen93.cloud.seata.entity.SeataT2;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xuchen.wang
 * @date 2023/12/28
 */
@Service
@Slf4j
public class Busi2Service {

    @Autowired
    SeataT2Service seataT2Service;

    public void success(){
        logRqId();
        ThreadUtil.sleep(1000);
        seataT2Service.save(new SeataT2(){{
            setVarV1(DateUtil.now()+"success");
            setIntV1(1);
        }});
    }

    public void fail(){
        logRqId();
        seataT2Service.save(new SeataT2(){{
            setVarV1(DateUtil.now()+"fail");
            setIntV1(1);
        }});
        int i = 1/0;
    }

    private void logRqId(){
        String xid = RootContext.getXID();
        log.warn("当前的分布式事务id："+xid);
    }
}
