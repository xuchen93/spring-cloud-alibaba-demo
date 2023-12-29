package com.github.xuchen93.cloud.controller;

import com.github.xuchen93.cloud.model.R;
import com.github.xuchen93.cloud.seata.service.Busi2Service;
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
    Busi2Service busi2Service;

    @GetMapping("success")
    public R<String> successCommit() {
        log.info("service2 success");
        busi2Service.success();
        return R.success();
    }

    @GetMapping("fail")
    public R<String> failCommit() {
        log.info("service2 fail");
        busi2Service.fail();
        return R.success();
    }
}
