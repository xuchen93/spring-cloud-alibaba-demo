package com.github.xuchen93.cloud.seata.service;

import cn.hutool.core.date.DateUtil;
import com.github.xuchen93.cloud.seata.entity.SeataT2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xuchen.wang
 * @date 2023/12/28
 */
@Service
public class Busi2Service {

    @Autowired
    SeataT2Service seataT2Service;

    public void success(){
        seataT2Service.save(new SeataT2(){{
            setVarV1(DateUtil.now()+"success");
            setIntV1(1);
        }});
    }

    public void fail(){
        seataT2Service.save(new SeataT2(){{
            setVarV1(DateUtil.now()+"fail");
            setIntV1(1);
        }});
        int i = 1/0;
    }
}
