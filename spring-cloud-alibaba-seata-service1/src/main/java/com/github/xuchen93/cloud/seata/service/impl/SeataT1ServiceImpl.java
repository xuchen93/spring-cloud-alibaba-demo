package com.github.xuchen93.cloud.seata.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.xuchen93.cloud.seata.dao.SeataT1Dao;
import com.github.xuchen93.cloud.seata.entity.SeataT1;
import com.github.xuchen93.cloud.seata.service.SeataT1Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Slf4j
@Service
@Transactional
public class SeataT1ServiceImpl extends ServiceImpl<SeataT1Dao, SeataT1> implements SeataT1Service {

    @PostConstruct
    public void init(){
        this.remove(new QueryWrapper<>());
        log.info("【清洗t1表】");
    }
}
