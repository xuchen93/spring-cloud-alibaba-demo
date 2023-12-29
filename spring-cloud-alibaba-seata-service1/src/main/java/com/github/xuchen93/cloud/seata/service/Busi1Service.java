package com.github.xuchen93.cloud.seata.service;

import cn.hutool.core.date.DateUtil;
import com.github.xuchen93.cloud.seata.entity.SeataT1;
import com.github.xuchen93.cloud.seata.feign.Service2Remote;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xuchen.wang
 * @date 2023/12/28
 */
@Service
public class Busi1Service {

    @Autowired
    Service2Remote service2Remote;

    @Autowired
    SeataT1Service seataT1Service;

    public void success(){
        seataT1Service.save(new SeataT1(){{
            setVarV1(DateUtil.now()+"success");
            setIntV1(1);
        }});
        service2Remote.success();
    }

    @GlobalTransactional(name = "fail1_test",rollbackFor = Exception.class)
    public void fail1(){
        service2Remote.success();
        seataT1Service.save(new SeataT1(){{
            setVarV1(DateUtil.now()+"fail1");
            setIntV1(1);
        }});
        int i = 1/0;
    }

    @GlobalTransactional(name = "fail2_test",rollbackFor = Exception.class)
    public void fail2(){
        seataT1Service.save(new SeataT1(){{
            setVarV1(DateUtil.now()+"fail2");
            setIntV1(1);
        }});
        service2Remote.fail();
    }

}
