package com.github.xuchen93.cloud.seata.controller;

import com.github.xuchen93.cloud.model.R;
import com.github.xuchen93.cloud.seata.service.Busi1Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuchen.wang
 * @date 2023/12/27
 */
@Slf4j
@RestController
public class TransactionalController {


    @Autowired
    Busi1Service busi1Service;

    @GetMapping("success")
    public R<String> successCommit(){
        log.info("service1 success");

        busi1Service.success();
        return R.success();
    }

    @GetMapping("fail1")
    public R<String> fail1(){
        log.info("service1 fail1");
        busi1Service.fail1();
        return R.success();
    }

    @GetMapping("fail2")
    public R<String> fail2(){
        log.info("service1 fail2");
        busi1Service.fail2();
        return R.success();
    }
}
